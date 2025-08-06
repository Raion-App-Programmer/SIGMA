package com.example.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NewsViewModel : ViewModel() {
    private val _newsList = MutableStateFlow<List<NewsItem>>(emptyList())
    val newsList: StateFlow<List<NewsItem>> get() = _newsList

    private val _newsItem = MutableLiveData<NewsItem?>()
    val newsItem: LiveData<NewsItem?> get() = _newsItem

    private val firestore = FirebaseFirestore.getInstance()

    init {
        fetchNews()
    }

    // fetch all news
    private fun fetchNews() {
        firestore.collection("laporan")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.e("Firestore", "Error fetching news", e)
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val newsItems = snapshot.documents.map { doc ->
                        NewsItem(
                            id = doc.id,
                            buktiUrl = doc.getString("buktiUrl") ?: "",
                            tanggal = doc.getString("tanggal") ?: "",
                            judul =  doc.getString("judul") ?: "",
                            nama =  doc.getString("nama") ?: "",
                            deskripsi =  doc.getString("deskripsi") ?: ""
                        )
                    }
                    _newsList.value = newsItems
                }
            }
    }

    // fetch items by id
    fun getNewsById(newsId: String) {
        firestore.collection("laporan")
            .document(newsId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val news = document.toObject(NewsItem::class.java)
                    _newsItem.value = news
                    Log.d("Firestore", "News found: $news")
                } else {
                    Log.e("Firestore", "News not found for ID: $newsId")
                    _newsItem.value = null
                }
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error fetching news", e)
                _newsItem.value = null
            }
    }
}
