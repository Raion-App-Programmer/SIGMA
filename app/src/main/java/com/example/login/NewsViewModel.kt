package com.example.login // Adjust based on your package

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class NewsViewModel : ViewModel() {
    private val _newsList = MutableStateFlow<List<NewsItem>>(emptyList())
    open val newsList: StateFlow<List<NewsItem>> get() = _newsList

    init {
        fetchNews()
    }

    private fun fetchNews() {
        FirebaseFirestore.getInstance().collection("news").addSnapshotListener { value, error ->
            if (error != null) {
                Log.e("Firestore", "Error fetching news", error)
                return@addSnapshotListener
            }
            val news = value?.documents?.mapNotNull { it.toObject(NewsItem::class.java) } ?: emptyList()
            _newsList.value = news
        }
    }
}
