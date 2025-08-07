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
import com.example.login.fitur_panduan.buttomNavbarPanduan
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

data class PointWithBullets(
    val title: String,
    val bullets: List<String>
)

@Composable
fun PanduanGempa(navController: NavController) {
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
                    painter = painterResource(id = R.drawable.gempa),
                    contentDescription = "Gempa icon",
                    modifier = Modifier
                        .width(55.dp)
                        .height(55.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Panduan Menghadapi\nGempa",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
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
                    .padding(top = 210.dp, bottom = 20.dp)
                    .width(372.dp)
                    .height(190.dp)
            ) {
                YouTubeVideoPlayerGempa()
            }
            Text(
                text = "Tips Aman Menghadapi Bencana\nGempa",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 10.dp, bottom = 5.dp)
            )
            Text(
                text = "CNN Indonesia ৹ 25 Februari 2022",
                modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 5.dp, bottom = 10.dp),
                fontWeight = FontWeight.Light,
                fontSize = 12.sp
            )

            Column(
                modifier = Modifier
                    .width(372.dp)
                    .height(368.dp)
                    .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(20.dp))
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    "Gempa bumi adalah bencana alam yang dapat terjadi secara tiba-tiba, menyebabkan kerusakan besar dan mengancam keselamatan jiwa. Oleh karena itu, penting untuk mengetahui langkah-langkah yang harus diambil saat menghadapi situasi ini.",
                    Modifier.padding(start = 10.dp,
                        top = 10.dp,
                        end = 10.dp,
                        bottom = 10.dp),
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Justify
                )
                Text(
                    "Tips Aman Saat Gempa Bumi",
                    Modifier.padding(start = 10.dp, end = 10.dp,top = 15.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                val pointList = listOf(
                    PointWithBullets(
                        "Berlindung:",
                        listOf(
                            "Cari perlindungan di bawah meja atau perabot yang kokoh. Jika tidak ada, merunduklah dan lindungi kepala dengan bantal atau lengan."
                        )
                    ),
                    PointWithBullets(
                        "Tetap di Dalam Ruangan:",
                        listOf(
                            "Tunggu hingga guncangan berhenti sebelum keluar. Hindari menggunakan lift selama dan setelah gempa."
                        )
                    ),
                    PointWithBullets(
                        "Jika di Luar Ruangan:",
                        listOf(
                            "Jauhi gedung, pohon, papan reklame, lampu jalan, dan jaringan berkabel untuk menghindari bahaya dari reruntuhan."
                        )
                    ),
                    PointWithBullets(
                        "Jika Terjebak dalam Reruntuhan:",
                        listOf(
                            "Jangan menyalakan api dan tutup mulut dengan saputangan jika ada. Cobalah untuk membuat suara agar tim SAR dapat menemukan posisi Anda."
                        )
                    )
                )

                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
                    pointList.forEachIndexed { index, item ->
                        NumberedWithBullets(index + 1, item.title, item.bullets)
                    }
                }

                Text(
                    "Pentingnya Ketahanan Emosional",
                    Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    """
                Ketahanan emosional saat menghadapi gempa sangat penting karena membantu seseorang tetap tenang, berpikir jernih, dan bertindak secara rasional dalam situasi darurat. Dengan mengelola stres dan menghindari kepanikan, seseorang dapat mengambil keputusan yang tepat untuk menyelamatkan diri dan orang lain. Selain itu, ketahanan emosional juga berperan dalam pemulihan pasca-bencana, mengurangi risiko trauma, serta membantu individu dan komunitas bangkit kembali dengan lebih cepat dan efektif.
                """.trimIndent(),
                    Modifier.padding(start = 10.dp, end = 10.dp),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Justify
                )
                Spacer(
                    modifier = Modifier
                        .height(100.dp)
                )
            }
        }
        buttomNavbarPanduan(navController)
    }
}
@Composable
fun NumberedWithBullets(number: Int, title: String, bullets: List<String>) {
    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        Row {
            Text(
                text = "$number.",
                fontSize = 15.sp,
                modifier = Modifier.width(24.dp),
                fontWeight = FontWeight.Medium
            )
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
        }

        bullets.forEach { bullet ->
            Row(modifier = Modifier.padding(start = 24.dp, top = 4.dp, end = 8.dp)) {
                Text(text = "•", fontSize = 15.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = bullet,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1f)
                )
            }
        }
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