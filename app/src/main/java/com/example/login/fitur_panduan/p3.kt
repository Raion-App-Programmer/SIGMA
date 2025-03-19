package com.example.login.fitur_panduan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.R
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.login.Routes
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


@Composable
fun p3(navController: NavController) {
    Box(
        modifier = Modifier
            .width(412.dp)
            .height(917.dp)
            .background(color = Color(0xFFF7EAEB))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
        ) {
            Box(
                modifier = Modifier
                    .width(412.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                    .shadow(elevation = 11.dp, spotColor = Color(0x40000000), ambientColor = Color(0x40000000))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFFC41532),
                                Color(0xFF431B3B)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier
                            .height(24.dp)
                            .clickable {
                                navController.navigate(Routes.Dashboard)
                            }
                    )

                    Spacer(
                        modifier = Modifier
                            .width(26.dp)
                    )
                    Image(painter = painterResource(id = R.drawable.p3k_darurat),
                        contentDescription = "Gempa darurat png", modifier = Modifier
                            .width(55.dp)
                            .height(55.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .width(20.dp)
                    )
                    Text(
                        text = "Panduan Pertolongan\nPertama",
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFF5F5F5),

                        )
                }
            }
            Spacer(modifier = Modifier.height(25.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //VideoPlayer() goes here
                YouTubeVideoPlayerP3()

                Spacer(modifier = Modifier
                    .height(20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 34.dp)
                ) {
                    Text(
                        text = "Ayo Belajar Pertolongan Utama",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(start = 5.dp, end = 5.dp)
                    )
                    Text(
                        text = "Ayo Belajar Pertolongan Pertama ৹ 03 April 2021",
                        modifier = Modifier
                            .padding(start = 5.dp, end = 5.dp),
                        fontWeight = FontWeight.Light, fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Card(
                    modifier = Modifier
                        .width(372.dp)
                        .height(350.dp)
                        .shadow(
                            elevation = 4.dp,
                            spotColor = Color(0x40000000),
                            ambientColor = Color(0x40000000)
                        ),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            "Pertolongan pertama adalah tindakan awal untuk membantu orang yang sakit atau terluka sebelum bantuan medis tiba. Tujuannya adalah menyelamatkan nyawa, mencegah kerusakan lebih lanjut, dan mendukung kesembuhan.",
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Justify
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            "Siapa yang Bisa Memberikan Pertolongan Pertama?",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            """
                1. Semua orang dapat memberikan pertolongan pertama, bukan hanya
                tenaga medis. Penting untuk mengikuti panduan dengan benar.
                """.trimIndent(),
                            fontSize = 15.sp,
                            textAlign = TextAlign.Justify
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            "Hal yang Harus Diingat",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            """
                        1. Amati Situasi: Utamakan keselamatan diri dan orang lain.
                        2. Pastikan Aman: Tindakan harus dilakukan dalam kondisi aman.
                        3. Prioritaskan Luka Serius: Tangani luka yang mengancam nyawa terlebih dahulu.
                        4. Minta Bantuan: Jangan ragu untuk meminta bantuan jika diperlukan.
                """.trimIndent(),
                            fontSize = 15.sp,
                            textAlign = TextAlign.Justify
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            "Contoh Kasus",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            """
                            - Rudi Tersedak: Ibunya melakukan Heimlich Maneuver.
                            - Rudi Mimisan: Seorang kakek memberikan bantuan yang tepat.
                            - Teman Rudi Pingsan: Rudi melonggarkan pakaian dan mengangkat kakinya.
                """.trimIndent(),
                            fontSize = 15.sp,
                            textAlign = TextAlign.Justify
                        )
                    }
                }
            }

        }
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        buttomNavbarPanduan(navController)
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
