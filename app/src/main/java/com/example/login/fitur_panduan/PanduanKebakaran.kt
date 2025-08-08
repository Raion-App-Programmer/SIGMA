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
fun PanduanKebakaran(navController: NavController) {
    val scrollState = rememberScrollState()
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
                    painter = painterResource(id = R.drawable.kebakaran),
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

            Box( Modifier
                .padding(top = 210.dp, bottom = 20.dp)
                .width(372.dp)
                .height(190.dp)
            ) {
                //VideoPlayer() goes here
                VideoPlayerKebakaran()

            }
            Text("Apa Yang Harus Dilakukan Saat \nTerjadi Kebakaran?", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier
                .padding(start = 5.dp, end = 5.dp, top = 10.dp, bottom = 5.dp))
            Text("BPBD Kabupaten Bogor   ৹   08 September 2021", modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 5.dp, bottom = 10.dp), fontWeight = FontWeight.Light, fontSize = 12.sp)
            Column(
                Modifier
                    .width(372.dp)
                    .height(368.dp)
                    .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(size = 20.dp))
                    .verticalScroll(rememberScrollState())
            )
            // Box container for text
            {
                Text("Kebakaran adalah bencana yang dapat terjadi kapan saja dan di mana saja, dan dapat mengancam keselamatan jiwa serta harta benda. Oleh karena itu, penting untuk mengetahui langkah-langkah yang harus diambil saat menghadapi situasi darurat ini.",
                    Modifier.padding(start = 10.dp,
                        top = 10.dp,
                        end = 10.dp,
                        bottom = 10.dp), fontWeight = FontWeight(400), fontSize = 15.sp)

                val poinList = listOf(
                    "Tetap Tenang: Jangan panik. Kepanikan dapat memperburuk situasi. Cobalah untuk tetap tenang dan berpikir jernih.",
                    "Matikan Peralatan Listrik: Jika aman untuk melakukannya, segera matikan arus listrik untuk mencegah kebakaran semakin meluas.",
                    "Lindungi Saluran Pernapasan: Gunakan masker atau kain untuk menutupi hidung dan mulut agar terhindar dari asap berbahaya.",
                    "Gunakan Alat Pemadam Api: Jika api masih kecil dan dapat dijangkau, gunakan alat pemadam api untuk memadamkannya. Pastikan Anda tahu cara menggunakannya.",
                    "Hubungi Petugas Pemadam Kebakaran: Segera hubungi dinas pemadam kebakaran. Berikan informasi yang jelas tentang lokasi dan situasi kebakaran.",
                    "Ikuti Petunjuk Petugas: Saat petugas tiba, ikuti instruksi mereka untuk memastikan keselamatan Anda dan orang lain.",
                    "Hindari Kerumunan: Jangan berkumpul di satu tempat, karena ini dapat menghambat tim penyelamat dan meningkatkan risiko bahaya.",
                    "Jaga Diri dan Sesama: Dalam situasi darurat, saling menjaga dan membantu satu sama lain sangat penting."
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

                    Text(
                        text = "Dengan mengetahui langkah-langkah ini, kita dapat meningkatkan peluang keselamatan diri dan orang-orang disekitar kita saat mengahadapi kebakaran. Selalu ingat untuk melakukan simulasi dan pelatihan kebakaran secara berkala agar siap menghadapi situasi darurat",
                        fontSize = 15.sp,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(100.dp)
                )
            }
        }
    }
    buttomNavbarPanduan(navController, context)
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
