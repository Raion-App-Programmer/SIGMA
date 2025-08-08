package com.example.login  // Ganti sesuai package kamu

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.profile.ProfileItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class ProfileViewModel : ViewModel() {

    private val _nama = MutableStateFlow("")
    val nama: StateFlow<String> get() = _nama

    private val _buktiUrl = MutableStateFlow("")
    val buktiUrl: StateFlow<String> get() = _buktiUrl

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> get() = _email

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    init {
        fetchProfile()
    }

    fun loadData() {
        viewModelScope.launch {
            try {
                val uid = auth.currentUser?.uid
                if (uid != null) {
                    val doc = db.collection("data profile").document(uid).get().await()
                    _nama.value = doc.getString("nama") ?: "Tidak diketahui"
                    _email.value = doc.getString("email") ?: "Tidak diketahui"
                    _buktiUrl.value = doc.getString("buktiUrl") ?: ""
                } else {
                    _nama.value = "Pengguna belum login"
                    _email.value = "-"
                    _buktiUrl.value = ""
                }
            } catch (e: Exception) {
                _nama.value = "Error"
                _email.value = e.localizedMessage ?: "Gagal mengambil email"
                _buktiUrl.value = e.localizedMessage ?: "Gagal mengambil email"
            }
        }
    }

    private val _profileList = MutableStateFlow<List<ProfileItem>>(emptyList())
    val profileList: StateFlow<List<ProfileItem>> get() = _profileList

    private val _profileItem = MutableLiveData<ProfileItem?>()
    val profileItem: LiveData<ProfileItem?> get() = _profileItem

    private fun fetchProfile() {
        db.collection("data profile")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.e("Firestore", "Error fetching news", e)
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val profileItems = snapshot.documents.map { doc ->
                        ProfileItem(
                            id = doc.id,
                            buktiUrl = doc.getString("buktiUrl") ?: "",
                            alamat = doc.getString("alamat") ?: "",
                            email =  doc.getString("email") ?: "",
                            nama =  doc.getString("nama") ?: "",
                            nomorTelepon = doc.getString("nomorTelepon") ?: "",
                        )
                    }
                    _profileList.value = profileItems
                }
            }
    }
}
