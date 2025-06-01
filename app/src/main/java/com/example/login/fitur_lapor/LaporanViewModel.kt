package com.example.login.fitur_lapor

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

data class Laporan(
    val nama: String,
    val tanggal: String,
    val waktu: String,
    val lokasi: String,
    val deskripsi: String,
    val buktiUrl: String,
    val judul: String,
    val uid: String,
    val status: String
)

class LaporanViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    var nama: MutableState<String> = mutableStateOf("")
    var tanggal: MutableState<String> = mutableStateOf("")
    var waktu: MutableState<String> = mutableStateOf("")
    var lokasi: MutableState<String> = mutableStateOf("")

    var deskripsi:  MutableState<String> = mutableStateOf("")
    var buktiUrl:  MutableState<String> = mutableStateOf("")
    var judul:  MutableState<String> = mutableStateOf("")
    var buktiUri : MutableState<Uri?> = mutableStateOf(null)
    var selectedFileName: MutableState<String> = mutableStateOf("Unggah Media")
    var uid: MutableState<String> = mutableStateOf("")
    var status: MutableState<String> = mutableStateOf("Menunggu persetujuan")

    private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        uid.value = firebaseAuth.currentUser?.uid.orEmpty()
    }

    init {
        uid.value = auth.currentUser?.uid.orEmpty()
        auth.addAuthStateListener(authStateListener)
    }

    fun toMap(): Map<String, Any> {
        return mapOf(
            "nama" to nama.value,
            "tanggal" to tanggal.value,
            "waktu" to waktu.value,
            "lokasi" to lokasi.value,
            "judul" to judul.value,
            "deskripsi" to deskripsi.value,
            "buktiUrl" to (buktiUrl.value.ifEmpty { "" }), // Pastikan tidak null
            "uid" to uid.value,
            "status" to status.value
        )
    }

    fun resetLaporan() {
        nama.value = ""
        tanggal.value = ""
        waktu.value = ""
        lokasi.value = ""
        deskripsi.value = ""
        buktiUrl.value = ""
        judul.value = ""
        buktiUri.value = null
        selectedFileName.value = "Unggah Media"
        // uid dan status tidak perlu direset
    }

}

fun saveLaporanToFirestore(
    laporan: Map<String, Any>,
    onSuccess: () -> Unit,
    onFailure: (Exception) -> Unit
) {
    val db = FirebaseFirestore.getInstance()
    db.collection("laporan")
        .add(laporan) // Sekarang Firestore bisa menerima laporan dalam bentuk Map
        .addOnSuccessListener {
            Log.d("Firestore", "Laporan berhasil disimpan!")
            onSuccess()
        }
        .addOnFailureListener { exception ->
            Log.e("Firestore", "Gagal menyimpan laporan: ${exception.message}")
            onFailure(exception)
        }
}



