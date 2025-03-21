package com.example.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _otp = MutableStateFlow("")
    val otp: StateFlow<String> = _otp

    fun generateOTP(): String {
        val generatedOTP = (100000..999999).random().toString()
        _otp.value = generatedOTP
        return generatedOTP
    }

    fun sendOTP(email: String, otp: String) {
        println("Sending $otp to $email")
    }

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    fun checkAuthStatus() {
        if (auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
        }
    }

    fun login(email: String, password: String, navController: NavController) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.LoginSuccess
                    navController.navigate(Routes.LoginBerhasil)
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email atau password tidak boleh kosong")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.LoginSuccess
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Terjadi kesalahan")
                }
            }
    }

    fun signUp(email: String, password: String, displayName: String, onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        Log.d("SignUp", "signUp called with email: $email")
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("SignUp", "Signup successful")
                    val user = FirebaseAuth.getInstance().currentUser
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(displayName)
                        .build()

                    user?.updateProfile(profileUpdates)?.addOnCompleteListener { profileTask ->
                        if (profileTask.isSuccessful) {
                            Log.d("SignUp", "Profile update successful")
                            onSuccess(user.uid)
                        } else {
                            Log.e("SignUp", "Profile update failed: ${profileTask.exception?.message}")
                            onFailure(profileTask.exception?.message ?: "Profile update failed")
                        }
                    }

                } else {
                    Log.e("SignUp", "Signup failed: ${task.exception?.message}")
                    onFailure(task.exception?.message ?: "Signup failed")
                }
            }
    }

    fun signout() {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }
}

sealed class AuthState {
    object SignUpSuccess : AuthState()
    object LoginSuccess : AuthState()
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}