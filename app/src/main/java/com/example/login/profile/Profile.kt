import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.login.NewsViewModel
import com.example.login.R
import com.example.login.Routes
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Profile(navController: NavController, viewModel: NewsViewModel = viewModel()) {

    val newsList by viewModel.newsList.collectAsState()
    Log.d("ProfileDebug", "newsList size: ${newsList.size}")
    newsList.forEach { item ->
        Log.d("ProfileDebug", "News UID: ${item.uid}, Judul: ${item.judul}")
    }

    val currentUid = FirebaseAuth.getInstance().currentUser?.uid
    val laporanSaya = newsList.filter { it.uid == currentUid }
    Log.d("ProfileDebug", "Current UID: $currentUid")
    Log.d("ProfileDebug", "lapporan: ${laporanSaya.size}")

    var userName by remember { mutableStateOf("Loading...") }
    var userEmail by remember { mutableStateOf("Loading...") }

    // Ambil data user sekali ketika Composable dipasang
    LaunchedEffect(Unit) {
        FirebaseAuth.getInstance().currentUser?.let { user ->
            userName = user.displayName ?: "Nama tidak tersedia"
            userEmail = user.email ?: "Email tidak tersedia"
        }
    }

    Box( // Use Box for overlapping effect
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0XFFF7EAEB))
    ) {
        // Head NavBar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(244.dp)
                .clip(RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color(0XFFC41532),
                            Color(0XFF431B3B)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Profil",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.offset(y = (-50).dp)
            )
            Image(
                painter = painterResource(id = R.drawable.gear_settings),
                contentDescription = "Gear Settings",
                modifier = Modifier
                    .size(24.dp)
                    .offset(y = (-50).dp, x = 140.dp)
//                    .clickable(
//                        navController.navigate(Routes)
//                   )
            )
        }

        // White box (Overlapping)
        Column( // Use Column for vertical layout within the Box
            modifier = Modifier
                .align(Alignment.TopCenter) // Align to top center
                .padding(top = 150.dp) // Adjust top padding to overlap
        ) {
            Box(
                modifier = Modifier
                    .width(371.dp)
                    .height(149.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color.White)
            ) {
                // Profile image and text inside the white box
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "$userName",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = "$userEmail",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Box (
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .background(Color(0XFF431B3B), shape = RoundedCornerShape(10.dp))
                            .width(120.dp)
                            .clickable {
                                navController.navigate(Routes.UbahProfile)
                            }
                    ) {

                        Text(
                            text = "Ubah Profil",
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight(700),
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(top = 16.dp)
                        )
                    }
                }
            }
            // Profile picture container
            Box (
                modifier = Modifier
                    .offset(y = (-215).dp, x = 140.dp)
                    .border(5.dp, color = Color.White, RoundedCornerShape(50.dp)),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_picture_image),
                    contentDescription = "Profile Picture",
                    Modifier.size(100.dp)
                )
            }

            Box(
                modifier = Modifier
                    .offset(y = (-80).dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .width(371.dp)
                    .height(788.dp)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        "Lacak Laporanmu!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .offset(x = 100.dp)
                    )

                    Column(modifier = Modifier.padding(top = 8.dp)) {

                        laporanSaya.forEach { laporan ->
                            val title = laporan.judul
                            val date = laporan.tanggal
                            val time = laporan.waktu
                            val status = laporan.status

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .clip(RectangleShape)
                                    .background(Color.White)
                                    .border(0.5.dp, Color.LightGray)
                                    .padding(12.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(date, fontSize = 12.sp, color = Color.Gray)
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                time,
                                                fontSize = 12.sp,
                                                color = Color.White,
                                                modifier = Modifier
                                                    .background(Color(0xFF8D2A2A), RoundedCornerShape(8.dp))
                                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                                            )
                                        }
                                    }

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        val statusColor = when (status) {
                                            "Menunggu persetujuan" -> Color(0xFFFFC107)
                                            "Berhasil diunggah" -> Color(0xFF4CAF50)
                                            "Ditolak" -> Color(0xFFD32F2F)
                                            else -> Color.Gray
                                        }
                                        Text(status, fontSize = 14.sp, color = Color.Gray)
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Box(
                                            modifier = Modifier
                                                .size(10.dp)
                                                .background(statusColor, shape = CircleShape)
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                }


            }
        }
        // Bottom dashboard
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            // Bottom navigation bar background
            Image(
                painter = painterResource(id = R.drawable.rectangle_bottom_dashboard_colored),
                contentDescription = "Dashboard navigation bottom",
                modifier = Modifier
                    .width(412.dp)
                    .height(100.dp)
                    .offset(y = 10.dp)
            )

            // Row for navigation icons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(82.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .offset(
                            y = (-15).dp, x = (-75).dp
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.home_gray_png),
                        contentDescription = "Home button",
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .offset(x = 15.dp, y = 25.dp)
                            .clickable { navController.navigate("Dashboard") }
                    )
                    androidx.compose.material3.Text(
                        "Beranda",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF616161),
                        modifier = Modifier
                            .offset(x = 15.dp, y = 25.dp)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .offset(y = (-25).dp, x = 10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.note_gray),
                        contentDescription = "Edit button",
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .offset(y = 38.dp, x = (-41).dp)
                            .clickable {
                                navController.navigate(Routes.LaporSigma1)
                            }
                    )
                    androidx.compose.material3.Text(
                        "Lapor",
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF616161),
                        fontSize = 13.sp,
                        modifier = Modifier
                            .offset(x = (-40).dp, y = 35.dp)
                    )
                }

                // Floating button for calls
                Column(
                    modifier = Modifier
                        .offset(y = (-5).dp),
                    Arrangement.Center
                ) {
                    Button(modifier = Modifier
                        .width(60.dp)
                        .height(60.dp),
                        shape = CircleShape,
                        contentPadding = PaddingValues(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF431B3B)),
                        onClick = {
                            // taruh navigasi call disini
                        }
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.phone_call_white),
                            contentDescription = "Call SIGMA",
                            modifier = Modifier
                                .width(34.dp)
                                .height(33.dp)
                                .offset(y = (-2).dp),
                            Alignment.Center
                        )
                    }
                    androidx.compose.material3.Text(
                        text = "Darurat",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0XFF616161),
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .offset(x = 10.dp)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .offset(y = (-15).dp, x = (-10).dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.book_gray),
                        contentDescription = "Edit button",
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .offset(y = 30.dp, x = 27.dp)
                            .clickable {
                                navController.navigate("BeritaTerkini") {
                                    launchSingleTop = true
                                }
                            }
                    )
                    androidx.compose.material3.Text(
                        "Berita",
                        fontSize = 13.sp,
                        color = Color(0xFF616161),
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .offset(y = 25.dp, x = 28.dp)
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.offset(y = (-20).dp, x = 70.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.user_circle_red),
                        contentDescription = "Profile button",
                        modifier = Modifier
                            .width(36.dp)
                            .height(36.dp)
                            .offset(x = (-20).dp, y = (30.dp))
                            .clickable {
                                navController.navigate(Routes.Profile)
                            }
                    )
                    androidx.compose.material3.Text(
                        text = "Profil",
                        color = Color(0xFFC35660),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .offset(x = (-20).dp, y = 30.dp)
                    )
                }


            }
        }
    }
}

//@Preview (showBackground = true)
//@Composable
//fun ProfilePreview() {
//    val navController = NavController
//    Profile(navController)
//}
