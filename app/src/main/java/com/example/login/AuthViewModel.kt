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

    private val _nameError = MutableLiveData<String?>()
    val nameError: LiveData<String?> = _nameError

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

    private val _confirmPasswordError = MutableLiveData<String?>()
    val confirmPasswordError: LiveData<String?> = _confirmPasswordError

    fun clearErrors() {
        _nameError.value = null
        _emailError.value = null
        _passwordError.value = null
        _confirmPasswordError.value = null
    }

    fun validateSignUpInputs(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        clearErrors()
        var isValid = true

        if (name.isBlank()) {
            _nameError.value = "Nama tidak boleh kosong"
            isValid = false
        }
        if (email.isBlank()) {
            _emailError.value = "Email tidak boleh kosong"
            isValid = false
        }
        if (password.isBlank()) {
            _passwordError.value = "Kata sandi tidak boleh kosong"
            isValid = false
        } else if (!isValidPassword(password)) {
            _passwordError.value = "Kata sandi harus minimal 8 karakter, 1 huruf besar, 1 huruf kecil, dan 1 angka"
            isValid = false
        }
        if (confirmPassword.isBlank()) {
            _confirmPasswordError.value = "Konfirmasi kata sandi tidak boleh kosong"
            isValid = false
        } else if (password != confirmPassword) {
            _confirmPasswordError.value = "Kata sandi tidak cocok"
            isValid = false
        }

        return isValid
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}\$")
        return passwordPattern.matches(password)
    }

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

data class SignUpFormState(
    val nama: String = "",
    val email: String = "",
    val password: String = "",
    val konfirmasiPassword: String = "",

    val namaError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val konfirmasiError: String? = null,
)