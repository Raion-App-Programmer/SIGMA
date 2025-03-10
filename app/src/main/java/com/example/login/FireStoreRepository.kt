package com.example.login // Change this to match your package name

import android.annotation.SuppressLint
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

object FireStoreRepository {
    private val db = FirebaseFirestore.getInstance()
    private val newsCollection = db.collection("news")

    fun addNews(title: String, date: String, imageUrl: String, onComplete: (Boolean) -> Unit) {
        val newsData = hashMapOf(
            "title" to title,
            "date" to date,
            "imageUrl" to imageUrl
        )
        newsCollection.add(newsData)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener {onComplete(false)}
    }
    @SuppressLint("SuspiciousIndentation")
    suspend fun getNews(): List<newsItem> {
        return try {
        val snapshot = newsCollection.get().await()
            snapshot.documents.map { doc ->
                newsItem (
                    id = doc.id,
                    title = doc.getString("title") ?: "",
                    date = doc.getString("date") ?: "",
                    imageUrl = doc.getString("imageUrl") ?: ""
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}