package com.example.login.admin

import com.example.login.R
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.motion.widget.MotionScene.Transition.TransitionOnClick
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.cloudinary.transformation.resize.Thumbnail
import com.example.login.NewsViewModel
import com.example.login.ui.theme.LoginTheme
import com.google.firebase.auth.FirebaseAuth

data class NewsItem(
    val id: String,
    val date: String,
    val title: String,
    val author: String,
    val status: NewsStatus,
    val imageRes: String,
    val imageResS: List<String>
)

enum class NewsStatus(val label: String, val color: Color) {
    Pending("Menunggu Konfirmasi", Color(0xFFFFA726)),
    Rejected("Ditolak", Color(0xFFEF5350)),
    Confirmed("Terkonfirmasi", Color(0xFF66BB6A))
}

//val dummyNews = listOf(
//    NewsItem("Selasa, 11 Maret 2025", "Maling Motor Singosari.", "Riana", NewsStatus.Pending, "0"),
//    NewsItem("Minggu, 9 Maret 2025", "Longsor JLS Malang Boloo.", "Kiki", NewsStatus.Pending, "0"),
//    NewsItem("Sabtu, 8 Maret 2025", "Pohon Tumbang Jl. Veteran.", "Suki", NewsStatus.Rejected, "0"),
//    NewsItem("Kamis, 6 Maret 2025", "Suhat Banjir Terus, Rek.", "Diandra", NewsStatus.Confirmed, "0")
//)
fun logoutUser(navController: NavController) {
    FirebaseAuth.getInstance().signOut()
    navController.navigate("loginMasuk")
}

@Composable
fun NewsConfirmationScreen(navController: NavController, viewModel: NewsViewModel = viewModel()) {
    val firestoreNews by viewModel.newsList.collectAsState()

    val newsList = firestoreNews.map {
        NewsItem(
            id = it.id,
            date = it.tanggal,
            title = it.judul,
            author = it.nama,
            status = when (it.status) {
                "Menunggu persetujuan" -> NewsStatus.Pending
                "Ditolak" -> NewsStatus.Rejected
                "Berhasil diunggah" -> NewsStatus.Confirmed
                else -> NewsStatus.Pending
            },
            imageRes = it.buktiUrl,
            imageResS = it.buktiUrls

        )
//        Log.d("status = ",it.status)
    }
    Scaffold(
        topBar = {
            Surface(
                color = Color(0xFFCE0033),
                shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp),
                elevation = 4.dp,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Konfirmasi Berita", color = Color.White, fontSize = 20.sp, modifier = Modifier.padding(top = 20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp,end = 8.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(onClick = { logoutUser(navController)}) {
                            Icon(Icons.Default.ExitToApp, contentDescription = "Logout", tint = Color.White)
                        }
                    }
                }
            }
        }
    ) { contentPadding ->
        LazyColumn(
            contentPadding = PaddingValues(top = 16.dp, bottom = contentPadding.calculateBottomPadding()),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.background(Color(0xFFF5F5F7))
        ) {
            items(newsList) { news ->
                val thumbnailUrl = when {
                    !news.imageResS.isNullOrEmpty() -> news.imageResS[0].toString()
                    !news.imageRes.isNullOrEmpty() -> news.imageRes
                    else -> null
                }
                NewsCard(news = news, thumbnail = thumbnailUrl ?: "", onClick = {
                    navController.navigate("DetailPengajuan/${news.id}")
                })
            }
        }
    }
}

@Composable
fun NewsCard(news: NewsItem, thumbnail: String,onClick: ()->Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
    ) {
        val isPreview = LocalInspectionMode.current

        AsyncImage(
            model = thumbnail,
            contentDescription = "News Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp)),
            placeholder = painterResource(id = R.drawable.no_image_available), // Gambar default saat loading
            error = painterResource(id = R.drawable.no_image_available) // Gambar default saat gagal/error atau imageUrl kosong
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xB3000000), Color(0x80000000))
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(news.date, fontSize = 12.sp, color = Color.White.copy(alpha = 0.7f))

                Box(
                    modifier = Modifier
                        .background(color = news.status.color, shape = RoundedCornerShape(16.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        news.status.label,
                        fontSize = 10.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Column {
                Text(
                    text = news.title,
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.8f),
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(news.author, fontSize = 12.sp, color = Color.White.copy(alpha = 0.8f))
                    }
                    Text(
                        "Selengkapnya",
                        fontSize = 12.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .background(
                                Color.Black.copy(alpha = 0.3f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable { }
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true, widthDp = 360)
//@Composable
//fun PreviewNewsScreen() {
//    LoginTheme{
//        NewsConfirmationScreen(newsList = dummyNews)
//    }
//}
