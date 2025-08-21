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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.login.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun PanduanBanjir(navController: NavController) {
    val context = LocalContext.current

    // Main Background
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0XFFF5F5F5))
    ) {
        // header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                .height(130.dp)
                .align(Alignment.TopCenter)
//                .background(brush = Brush.horizontalGradient(
//                    listOf(
//                        Color(0XFFC41532),
//                        Color(0XFF431B3B)
//                    )
//                ))
                .background(Color(0xFFBF002E))
        ) {
            // Icons & Title
            Row(
                modifier = Modifier.fillMaxSize()
                    .offset(x = 21.dp)
                    .padding(top = 20.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    modifier = Modifier.width(55.dp).height(55.dp),
                    painter = painterResource(id = R.drawable.banjir),
                    contentDescription = "Banjir icon"
                )
                Spacer(modifier= Modifier.width(16.dp))
                Text("Panduan Menghadapi \nBanjir", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }

        // Video Container
            Column (
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = (-40).dp)
            ) {
            Box( Modifier
                .padding(top = 200.dp, bottom = 20.dp)
                .width(372.dp)
                .height(190.dp)
            ) {
                //VideoPlayer() goes here
                VideoPlayerBanjir()
            }
                Text("Tips Saat Terjadi Banjir", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp, top = 10.dp, bottom = 5.dp))
                Text("One Care ৹ 18 Maret 2017", modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 5.dp, bottom = 10.dp), fontWeight = FontWeight.Light, fontSize = 12.sp)

                Column(
                    Modifier
                        .width(372.dp)
                        .height(368.dp)
//                        .shadow(elevation = 4.dp, spotColor = Color(0x40000000), ambientColor = Color(0x40000000))
//                        .border(width = 1.dp, color = Color(0xFFD7D7D7), shape = RoundedCornerShape(size = 20.dp))
                        .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(size = 20.dp))
                        .verticalScroll(rememberScrollState()))
                // Box container for text
                {
                    Text("Banjir dapat menyebabkan kerugian besar, sehingga penting untuk mempersiapkan diri dan mengambil langkah pencegahan.",
                         Modifier.padding(start = 10.dp,
                             top = 10.dp,
                             end = 10.dp,
                             bottom = 10.dp), fontWeight = FontWeight(400), fontSize = 15.sp)
                    val poinList = listOf(
                        "Pantau Informasi: Ikuti berita terkini tentang cuaca dan potensi banjir.",
                        "Perlengkapan Darurat: Siapkan makanan, air, obat-obatan dan senter.",
                        "Dokumen Penting: Simpan dokumen di tempat yang aman dan tinggi.",
                        "Matikan Listrik: Matikan sumber listrik untuk mencegah risiko kebakaran.",
                        "Jalur Evakuasi: Kenali dan ingat jalur evakuasi di daerah Anda."
                    )
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Text(
                            text = "Persiapan Menghadapi Banjir",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(vertical = 15.dp)
                        )

                        poinList.forEachIndexed { index, point ->
                            NumberedParagraph(index + 1, point)
                        }
                    }
                    val poinList2 = listOf(
                        "Bersihkan Saluran Air: Rutin bersihkan saluran dan sungai dari sampah.",
                        "Tanam Pohon: Tanam pohon untuk meningkatkan resapan air.",
                        "Buang Sampah dengan Benar: Edukasi masyarakat tentang pentingnya membuang sampah pada tempatnya.",
                        "Kenali Ancaman: Pahami potensi banjir di daerah Anda dan lakukan mitigasi."
                    )
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Text(
                            text = "Pencegahan Banjir",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 15.dp)
                        )

                        poinList2.forEachIndexed { index, point ->
                            NumberedParagraph(index + 1, point)
                        }

                        Text(
                            text = "Dengan langkah-langkah ini, kita dapat mengurangi dampak banjir dan melindungi diri serta komunitas.",
                            fontSize = 15.sp,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.padding(top = 12.dp)
                        )

                        Spacer(modifier = Modifier.height(100.dp))
                    }
                }
            }
        }
    buttomNavbarPanduan(navController, context)
}

@Composable
fun NumberedParagraph(number: Int, content: String) {
    Row(modifier = Modifier.padding(bottom = 8.dp)) {
        Text(
            text = "$number.",
            fontSize = 15.sp,
            modifier = Modifier.width(24.dp),
            fontWeight = FontWeight.Medium
        )
        Text(
            text = content,
            fontSize = 15.sp,
            textAlign = TextAlign.Start,
        )
    }
}

// video player
@Composable
fun VideoPlayerBanjir() {
        AndroidView(
            factory = { context ->
                YouTubePlayerView(context).apply {
                    enableAutomaticInitialization = false // Mencegah UI default muncul
                    addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            // Load video tanpa menampilkan UI default
                            youTubePlayer.cueVideo("6Tx7Z0OIh9U", 0f)
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

@Preview
@Composable
fun PanduanBanjirReview() {
    val navController = rememberNavController()
    PanduanBanjir(navController)
}