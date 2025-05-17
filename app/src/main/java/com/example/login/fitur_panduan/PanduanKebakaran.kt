package com.example.login.fitur_panduan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.login.R
import com.example.login.Routes
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun PanduanKebakaran(navController: NavController) {
    val scrollState = rememberScrollState()



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
                .height(150.dp)
                .align(Alignment.TopCenter)
                .background(brush = Brush.horizontalGradient(
                    listOf(
                        Color(0XFFC41532),
                        Color(0XFF431B3B)
                    )
                ))
        ) {
            // Icons & Title
            Row(
                modifier = Modifier.fillMaxSize()
                    .offset(x = 21.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Arrow back",
                    tint = Color.White,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Routes.Dashboard)
                        }
                )
                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    modifier = Modifier.width(55.dp).height(55.dp),
                    painter = painterResource(id = R.drawable.kebakaran_darurat),
                    contentDescription = "Banjir icon"
                )
                Spacer(modifier= Modifier.width(16.dp))
                Text("Panduan Menghadapi \nKebakaran", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }

        // Video Container
        Column (
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-40).dp)
        ) {
            Spacer(modifier = Modifier.height(165.dp))

            Box( Modifier
                .width(372.dp)
                .height(190.dp)
                .padding(top = 10.dp)
            ) {
                //VideoPlayer() goes here
                VideoPlayerKebakaran()

            }
            Spacer(modifier = Modifier.height(12.dp))
            Text("Apa Yang Harus Dilakukan Saat \nTerjadi Kebakaran?", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier
                .padding(start = 5.dp, end = 5.dp))
            Spacer(modifier = Modifier.height(5.dp))
            Text("BPBD Kabupaten Bogor   ৹   08 September 2021", modifier = Modifier.padding(start = 5.dp, end = 5.dp), fontWeight = FontWeight.Light, fontSize = 12.sp)

            Spacer(modifier = Modifier.height(10.dp))
            Column(
                Modifier
                    .width(372.dp)
                    .height(368.dp)
                    .shadow(elevation = 4.dp, spotColor = Color(0x40000000), ambientColor = Color(0x40000000))
                    .border(width = 1.dp, color = Color(0xFFD7D7D7), shape = RoundedCornerShape(size = 20.dp))
                    .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(size = 20.dp))
                    .verticalScroll(rememberScrollState())
            )
            // Box container for text
            {
                Text("Kebakaran adalah bencana yang dapat terjadi kapan saja dan di mana saja, dan dapat mengancam keselamatan jiwa serta harta benda. Oleh karena itu, penting untuk mengetahui langkah-langkah yang harus diambil saat menghadapi situasi darurat ini.",
                    Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp), fontWeight = FontWeight(400), fontSize = 15.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Text("Langkah-Langkah yang Harus Diambil", Modifier
                    .padding(start = 10.dp, end = 10.dp),
                    fontWeight = FontWeight.Bold, fontSize = 15.sp )
                Spacer(modifier = Modifier.height (10.dp))
                Text("""
                        1. Tetap Tenang: Jangan panik. Kepanikan dapat memperburuk situasi. Cobalah untuk tetap tenang dan berpikir jernih.
                        2. Matikan Peralatan Listrik: Jika aman untuk melakukannya, segera matikan arus listrik untuk mencegah kebakaran semakin meluas.
                        3. Lindungi Saluran Pernapasan: Gunakan masker atau kain untuk menutupi hidung dan mulut agar terhindar dari asap berbahaya.
                        4. Gunakan Alat Pemadam Api: Jika api masih kecil dan dapat dijangkau, gunakan alat pemadam api untuk memadamkannya. Pastikan Anda tahu cara menggunakannya.
                        5. Hubungi Petugas Pemadam Kebakaran: Segera hubungi dinas pemadam kebakaran. Berikan informasi yang jelas tentang lokasi dan situasi kebakaran.
                        6. Ikuti Petunjuk Petugas: Saat petugas tiba, ikuti instruksi mereka untuk memastikan keselamatan Anda dan orang lain.
                        7. Hindari Kerumunan: Jangan berkumpul di satu tempat, karena ini dapat menghambat tim penyelamat dan meningkatkan risiko bahaya.
                        8. Jaga Diri dan Sesama: Dalam situasi darurat, saling menjaga dan membantu satu sama lain sangat penting.
                    """.trimIndent(), Modifier.padding(start = 10.dp, end = 10.dp), fontSize = 15.sp, textAlign = TextAlign.Justify)
                Spacer(modifier = Modifier.height(10.dp))


            }
        }
    }
    buttomNavbarPanduan(navController)
}



// video player
@Composable
fun VideoPlayerKebakaran() {
    AndroidView(
        factory = { context ->
            YouTubePlayerView(context).apply {
                enableAutomaticInitialization = false // Mencegah UI default muncul
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        // Load video tanpa menampilkan UI default
                        youTubePlayer.cueVideo("NihNPyDagKE", 0f)
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
fun PanduanKebakaranPreview() {
    val navController = rememberNavController()
    PanduanKebakaran(navController)
}
