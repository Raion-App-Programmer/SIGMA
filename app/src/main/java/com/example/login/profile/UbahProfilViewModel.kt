// File: D:/AndroidProgramming/SIGMA-main/SIGMA-main/app/src/main/java/com/example/login/fitur_profile/UbahProfilViewModel.kt

package com.example.login.profile

// File: D:/AndroidProgramming/SIGMA-main/SIGMA-main/app/src/main/java/com/example/login/profile/UbahProfilViewModel.kt
// Perhatikan ini adalah paket 'profile'

 // PASTIKAN BARIS INI BENAR

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UbahProfilViewModel : ViewModel() {
    val nama = mutableStateOf("")
    val email = mutableStateOf("")
    val kataSandi = mutableStateOf("")
    val nomorTelepon = mutableStateOf("")
    val alamat = mutableStateOf("")
    val buktiUrl = mutableStateOf<String?>(null)

    val reauthEmail = mutableStateOf("")
    val reauthPassword = mutableStateOf("")
    val showReauthDialog = mutableStateOf(false)

    fun toMap(): Map<String, Any?> {
        return mapOf(
            "nama" to nama.value,
            "email" to email.value,
            "nomorTelepon" to nomorTelepon.value,
            "alamat" to alamat.value,
            "buktiUrl" to buktiUrl.value
        )
    }

    fun loadProfileData(userId: String, context: Context) {
        val db = FirebaseFirestore.getInstance()
        db.collection("data profile").document(userId).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    Log.d("UbahProfilViewModel", "Dokumen ditemukan: ${document.data}")
                    nama.value = document.getString("nama") ?: ""
                    email.value = document.getString("email") ?: ""
                    nomorTelepon.value = document.getString("nomorTelepon") ?: ""
                    alamat.value = document.getString("alamat") ?: ""
                    buktiUrl.value = document.getString("buktiUrl")
                } else {
                    Log.d("UbahProfilViewModel", "No such document for user: $userId")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("UbahProfilViewModel", "Error getting profile data: ", exception)
            }
        Log.d("UbahProfilViewModel", "Data loaded: ${nama.value}, ${email.value}, ${nomorTelepon.value}, ${alamat.value}, ${buktiUrl.value}")

    }
}