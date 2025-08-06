package com.example.login  // Ganti sesuai package kamu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProfileViewModel : ViewModel() {

    private val _nama = MutableStateFlow("")
    val nama: String get() = _nama.value

    private val _email = MutableStateFlow("")
    val email: String get() = _email.value

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    fun loadData() {
        viewModelScope.launch {
            try {
                val uid = auth.currentUser?.uid
                if (uid != null) {
                    val doc = db.collection("data profile").document(uid).get().await()
                    _nama.value = doc.getString("nama") ?: "Tidak diketahui"
                    _email.value = doc.getString("email") ?: "Tidak diketahui"
                } else {
                    _nama.value = "Pengguna belum login"
                    _email.value = "-"
                }
            } catch (e: Exception) {
                _nama.value = "Error"
                _email.value = e.localizedMessage ?: "Gagal mengambil email"
            }
        }
    }
}
