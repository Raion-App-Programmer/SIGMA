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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.R
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.login.Routes
import com.example.login.fitur_panduan.buttomNavbarPanduan
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun panduanGempa(navController: NavController) {

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
                    .shadow(
                        elevation = 11.dp,
                        spotColor = Color(0x40000000),
                        ambientColor = Color(0x40000000)
                    )
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
                    Image(
                        painter = painterResource(id = R.drawable.gempa_darurat),
                        contentDescription = "Gempa darurat png", modifier = Modifier
                            .width(55.dp)
                            .height(55.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .width(20.dp)
                    )
                    Text(
                        text = "Panduan Menghadapi\nGempa",
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
                YouTubeVideoPlayerGempa()

                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 34.dp)
                ) {
                    Text(
                        text = "Tips Aman Menghadapi Bencana\nGempa",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(start = 5.dp, end = 5.dp)
                    )
                    Text(
                        text = "CNN Indonesia ৹ 25 Februari 2022",
                        modifier = Modifier
                            .padding(start = 5.dp, end = 5.dp),
                        fontWeight = FontWeight.Light, fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Card(
                    modifier = Modifier
                        .width(372.dp)
                        .height(340.dp)
                        .shadow(
                            elevation = 4.dp,
                            spotColor = Color(0x40000000),
                            ambientColor = Color(0x40000000)
                        ),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .verticalScroll(rememberScrollState()),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                "Gempa bumi adalah bencana alam yang dapat terjadi secara tiba-tiba, menyebabkan kerusakan besar dan mengancam keselamatan jiwa. Oleh karena itu, penting untuk mengetahui langkah-langkah yang harus diambil saat menghadapi situasi ini.",
                                fontWeight = FontWeight.Normal,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Justify
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                "Tips Aman Saat Gempa Bumi",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                """
                1. Berlindung:
                   - Cari perlindungan di bawah meja atau perabot yang kokoh. Jika tidak ada, merunduklah dan lindungi kepala dengan bantal atau lengan.
                2. Tetap di Dalam Ruangan:
                 - Tunggu hingga guncangan berhenti sebelum keluar. Hindari menggunakan lift selama dan setelah gempa.
                3. Jika di Luar Ruangan:
                   - Jauhi gedung, pohon, papan reklame, lampu jalan, dan jaringan berkabel untuk menghindari bahaya dari reruntuhan.
                4. Jika Terjebak dalam Reruntuhan:
                   - Jangan menyalakan api dan tutup mulut dengan saputangan jika ada. Cobalah untuk membuat suara agar tim SAR dapat menemukan posisi Anda. 
                """.trimIndent(),
                                fontSize = 15.sp,
                                textAlign = TextAlign.Justify
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                "Pentingnya Ketahanan Emosional",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                """
                Ketahanan emosional saat menghadapi gempa sangat penting karena membantu seseorang tetap tenang, berpikir jernih, dan bertindak secara rasional dalam situasi darurat. Dengan mengelola stres dan menghindari kepanikan, seseorang dapat mengambil keputusan yang tepat untuk menyelamatkan diri dan orang lain. Selain itu, ketahanan emosional juga berperan dalam pemulihan pasca-bencana, mengurangi risiko trauma, serta membantu individu dan komunitas bangkit kembali dengan lebih cepat dan efektif.
                """.trimIndent(),
                                fontSize = 15.sp,
                                textAlign = TextAlign.Justify
                            )
                        }
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
fun YouTubeVideoPlayerGempa() {
    AndroidView(
        factory = { context ->
            YouTubePlayerView(context).apply {
                enableAutomaticInitialization = false // Mencegah UI default muncul
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.cueVideo("l3h0eWK_Oek", 0f)
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