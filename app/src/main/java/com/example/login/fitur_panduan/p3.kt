package com.example.login.fitur_panduan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.R
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


@Composable
fun P3(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0XFFF5F5F5))
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                .height(120.dp)
                .align(Alignment.TopCenter)
                .background(Color(0xFFBF002E))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(x = 21.dp)
                    .padding(top = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.p3k),
                    contentDescription = "Gempa darurat png",
                    modifier = Modifier
                        .width(55.dp)
                        .height(55.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Panduan Pertolongan\nPertama",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        // Content
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-40).dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 185.dp, bottom = 20.dp)
                    .width(372.dp)
                    .height(190.dp)
            ) {
                YouTubeVideoPlayerP3()
            }
            Text(
                text = "Ayo Belajar Pertolongan Utama",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 10.dp, bottom = 5.dp)
            )
            Text(
                text = "Ayo Belajar Pertolongan Pertama ৹ 03 April 2021",
                modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 5.dp, bottom = 10.dp),
                fontWeight = FontWeight.Light,
                fontSize = 12.sp
            )
            Column(
                modifier = Modifier
                    .width(372.dp)
                    .height(350.dp)
                    .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(20.dp))
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    "Pertolongan pertama adalah tindakan awal untuk membantu orang yang sakit atau terluka sebelum bantuan medis tiba. Tujuannya adalah menyelamatkan nyawa, mencegah kerusakan lebih lanjut, dan mendukung kesembuhan.",
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    "Siapa yang Bisa Memberikan Pertolongan Pertama?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp)
                )
                Text(
                    """
                Semua orang dapat memberikan pertolongan pertama, bukan hanya
                tenaga medis. Penting untuk mengikuti panduan dengan benar.
                """.trimIndent(),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp)
                )
                val poinList = listOf(
                    "Amati Situasi: Utamakan keselamatan diri dan orang lain.",
                    "Pastikan Aman: Tindakan harus dilakukan dalam kondisi aman.",
                    "Prioritaskan Luka Serius: Tangani luka yang mengancam nyawa terlebih dahulu.",
                    "Minta Bantuan: Jangan ragu untuk meminta bantuan jika diperlukan.",
                )
                Text(
                    "Hal yang Harus Diingat",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    poinList.forEachIndexed { index, point ->
                        NumberedParagraph(index + 1, point)
                    }
                }
                Text(
                    "Contoh Kasus",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp)
                )
                val poinList2 = listOf(
                    "Rudi Tersedak: Ibunya melakukan Heimlich Maneuver.",
                    "Rudi Mimisan: Seorang kakek memberikan bantuan yang tepat.",
                    "Teman Rudi Pingsan: Rudi melonggarkan pakaian dan mengangkat kakinya.",
                )
                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                    poinList2.forEach { point ->
                        BulletParagraph(point)
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
        buttomNavbarPanduan(navController)
    }
}


@Composable
fun BulletParagraph(content: String) {
    Row(modifier = Modifier.padding(bottom = 8.dp, end = 8.dp)) {
        Text(text = "•", fontSize = 15.sp)
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = content,
            fontSize = 15.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun YouTubeVideoPlayerP3() {
    AndroidView(
        factory = { context ->
            YouTubePlayerView(context).apply {
                enableAutomaticInitialization = false // Mencegah UI default muncul
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        // Load video tanpa menampilkan UI default
                        youTubePlayer.cueVideo("YDN9JGgouOY", 0f)
                        youTubePlayer.unMute()
                    }
                })
            }
        },
        modifier = Modifier
            .width(372.dp)
            .height(190.dp)
            .clip(RoundedCornerShape(20.dp))
    )
}