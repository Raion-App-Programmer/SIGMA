package com.example.login // Adjust based on your package

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

object FireStoreRepository {
    private val db = FirebaseFirestore.getInstance()
    private val newsCollection = db.collection("news")

    fun addNews(title: String, date: String, imageUrl: String, description: String, onComplete: (Boolean) -> Unit) {
        val newsData = hashMapOf(
            "title" to title,
            "date" to date,
            "imageUrl" to imageUrl,
            "description" to description // Store description
        )
        newsCollection.add(newsData)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

    suspend fun getNews(): List<NewsItem> {
        return try {
            val snapshot = newsCollection.get().await()
            snapshot.documents.map { doc ->
                NewsItem(
                    id = doc.id,
                    judul = doc.getString("judul") ?: "",
                    tanggal = doc.getString("tangal") ?: "",
                    buktiUrl =  doc.getString("buktiUrl") ?: "",
                    deskripsi =  doc.getString("deskripsi") ?: "" // Fetch description
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
