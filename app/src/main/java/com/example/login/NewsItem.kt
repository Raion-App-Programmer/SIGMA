package com.example.login

data class NewsItem(
    val id: String = "",
    val buktiUrl: String = "",
    val buktiUrls: List<String> = emptyList<String>(),
    val tanggal: String = "",
    val judul: String = "",
    val nama: String = "",
    val deskripsi: String = "",
    val waktu: String = "",
    val uid: String = "",
    val status: String = ""
){
    fun getAllBuktiUrls(): List<String> {
        return when {
            !buktiUrls.isNullOrEmpty() -> buktiUrls
            !buktiUrl.isNullOrEmpty() -> listOf(buktiUrl)
            else -> emptyList()
        }
    }
}
