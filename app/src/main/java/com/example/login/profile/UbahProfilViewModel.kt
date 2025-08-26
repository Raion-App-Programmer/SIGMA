// File: app/src/main/java/com/example/login/profile/UbahProfilViewModel.kt
package com.example.login.profile

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class UbahProfilViewModel : ViewModel() {
    // State untuk data profil
    val nama = mutableStateOf("")
    val email = mutableStateOf("")
    val nomorTelepon = mutableStateOf("")
    val alamat = mutableStateOf("")
    val buktiUrl = mutableStateOf<String?>(null)
    val kataSandi = mutableStateOf("")

    // FIX 1: Ganti nama agar lebih jelas. Ini untuk field "Kata Sandi Baru" di form.
    val kataSandiBaru = mutableStateOf("")

    // State untuk dialog konfirmasi ulang (re-authentication)
    val reauthEmail = mutableStateOf("")
    val reauthPassword = mutableStateOf("")
    val showReauthDialog = mutableStateOf(false)

    // FIX 2: Tambahkan state untuk mengontrol tampilan loading indicator
    val isLoading = mutableStateOf(true)

    // Flag untuk memastikan data hanya di-load sekali
    private var isProfileLoaded = false

    /**
     * Mengonversi data state menjadi Map untuk disimpan ke Firestore.
     * Tidak menyertakan kata sandi.
     */
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "nama" to nama.value,
            "email" to email.value,
            "nomorTelepon" to nomorTelepon.value,
            "alamat" to alamat.value,
            "buktiUrl" to buktiUrl.value
        )
    }

    /**
     * Mengambil data profil dari Firestore.
     */
    fun loadProfileData(userId: String, context: Context) {
        // Jangan load ulang jika data sudah ada
        if (isProfileLoaded) {
            isLoading.value = false // Pastikan loading false jika kita tidak fetch ulang
            return
        }

        // Mulai proses loading
        isLoading.value = true
        val db = FirebaseFirestore.getInstance()

        db.collection("data profile").document(userId).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    Log.d("UbahProfilViewModel", "Dokumen ditemukan: ${document.data}")

                    // Isi state dengan data dari Firestore
                    nama.value = document.getString("nama") ?: ""
                    email.value = document.getString("email") ?: ""
                    nomorTelepon.value = document.getString("nomorTelepon") ?: ""
                    alamat.value = document.getString("alamat") ?: ""
                    buktiUrl.value = document.getString("buktiUrl") ?: ""

                    // FIX 3: Pindahkan log ke sini agar menampilkan data yang sudah diisi
                    Log.d("UbahProfilViewModel", "State diupdate: nama=${nama.value}, email=${email.value}")

                    isProfileLoaded = true
                } else {
                    Log.d("UbahProfilViewModel", "Dokumen tidak ditemukan untuk user: $userId")
                }
                // Hentikan loading setelah selesai (baik dokumen ada atau tidak)
                isLoading.value = false
            }
            .addOnFailureListener { exception ->
                Log.e("UbahProfilViewModel", "Gagal mengambil data profil: ", exception)
                // Hentikan loading jika terjadi error
                isLoading.value = false
            }
    }
}