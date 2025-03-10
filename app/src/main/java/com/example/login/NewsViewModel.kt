package com.example.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val repository = FireStoreRepository

    private val  _newsList = MutableStateFlow<List<newsItem>>(emptyList())
    val newsList = _newsList.asStateFlow()

    init {
        fetchNews()
    }

    private fun fetchNews() {
        val db = FirebaseFirestore.getInstance()
        db.collection("news")
            .get()
            .addOnSuccessListener { documents ->
                val news = documents.map { doc ->
                    newsItem(
                        title = doc.getString("title") ?: "",
                        date = doc.getString("date") ?: "",
                        imageUrl = doc.getString("imageUrl") ?: "",
                    )
                }
                _newsList.value = news
            }
            .addOnFailureListener { exception ->
                println("Error fetching news: ${exception.message}")
            }
    }
}
data class newsItem(
    val id: String = "",
    val title: String = "",
    val date: String = "",
    val imageUrl: String = "",
    val author: String = "",
    val content: String = ""
)

