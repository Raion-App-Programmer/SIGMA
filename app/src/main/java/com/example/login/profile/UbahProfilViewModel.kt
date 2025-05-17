package com.example.login.profile
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class UbahProfilViewModel : ViewModel() {
    val nama = mutableStateOf("")
    val email = mutableStateOf("")
    val kataSandi = mutableStateOf("")
    val nomorTelepon = mutableStateOf("")
    val alamat = mutableStateOf("")
    val buktiUrl = mutableStateOf("")


    fun toMap(): Map<String, Any> {
        return mapOf(
            "nama" to nama.value,
            "email" to email.value,
            "kataSandi" to kataSandi.value,
            "nomorTelepon" to nomorTelepon.value,
            "alamat" to alamat.value,
            "buktiUrl" to (buktiUrl.value.ifEmpty { "" })
        )
    }
    fun saveUbahProfileToFirestore(
        ubahProfile: Map<String, Any>,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val db = FirebaseFirestore.getInstance()
        db.collection("data profil")
            .add(ubahProfile) // Sekarang Firestore bisa menerima laporan dalam bentuk Map
            .addOnSuccessListener {
                Log.d("Firestore", "data profil berhasil disimpan!")
                onSuccess()
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Gagal menyimpan data profil: ${exception.message}")
                onFailure(exception)
            }
    }

}
