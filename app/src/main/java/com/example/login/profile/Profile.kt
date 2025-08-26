import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.login.NewsViewModel
import com.example.login.ProfileViewModel
import com.example.login.R
import com.example.login.Routes
import com.example.mytestsigma.ui.theme.getUserLocation
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.annotations.Async

@Composable
fun Profile(navController: NavController, viewModel: NewsViewModel = viewModel(), profileViewModel: ProfileViewModel = viewModel()) {

    val context = LocalContext.current
    val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true && permissions[Manifest.permission.CALL_PHONE] == true) {
            Toast.makeText(context, "Izin lokasi dan panggilan diberikan", Toast.LENGTH_SHORT).show()
            getUserLocation(context, navController)
        } else if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true) {
            Toast.makeText(context, "Izin lokasi diberikan, izin panggilan ditolak", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Izin lokasi ditolak", Toast.LENGTH_SHORT).show()
        }
    }
    // Ambil data dari StateFlow
    val nama by profileViewModel.nama.collectAsState()
    val email by profileViewModel.email.collectAsState()
    val buktiUrl by profileViewModel.buktiUrl.collectAsState()

    // Load data profil sekali saat pertama kali composable muncul
    LaunchedEffect(Unit) {
        profileViewModel.loadData()
    }

    // --- DATA LAPORAN ---
    val newsList by viewModel.newsList.collectAsState()
    val currentUid = FirebaseAuth.getInstance().currentUser?.uid
    val laporanSaya = newsList.filter { it.uid == currentUid }

    // --- UI STRUCTURE ---
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F0)) // A light grey background similar to the example
    ) {
        // Main content area that is scrollable
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 80.dp) // Add padding to avoid overlap with the bottom nav
        ) {
            // --- HEADER SECTION ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Adjusted height for the profile picture overlap
                    .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)) // Added rounded corners
                    .background(
                        Color(0XFFC41532)
                    ),
                contentAlignment = Alignment.TopCenter
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp, start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Spacer to help center the title, as there's no back button
                    Spacer(modifier = Modifier.size(24.dp))
                    Text(
                        text = "Profil",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Image(
                        painter = painterResource(id = R.drawable.gear_settings),
                        contentDescription = "Gear Settings",
                        modifier = Modifier.size(24.dp)
                        // .clickable { ... }
                    )
                }
            }

            // --- PROFILE & REPORTS SECTION ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-96).dp), // Pulls this section up to overlap the header
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Box to contain the white card and the overlapping profile picture
                Box(
                    contentAlignment = Alignment.TopCenter,
                    modifier = Modifier.padding(horizontal = 24.dp)
                ) {
                    // White Card for Profile Info, pushed down to make space for the image
                    Column(
                        modifier = Modifier
                            .padding(top = 48.dp) // Half of image size (100dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.White)
                            .padding(top = 66.dp, start = 16.dp, end = 16.dp, bottom = 16.dp), // Padding inside the card
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = nama,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = email,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { navController.navigate(Routes.UbahProfile) },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0XFFC41532)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                        ) {
                            Text(
                                text = "Ubah Profil",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    // Profile Picture, drawn on top of the Column
                    AsyncImage(
                        model = buktiUrl,
                        contentDescription = "Profile Picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .border(4.dp, Color.White, CircleShape),
                        placeholder = painterResource(id = R.drawable.profil_icon), // Gambar default saat loading
                        error = painterResource(id = R.drawable.profil_icon)
                    )
                    Log.d("Url Photo :", buktiUrl)
                }

                Spacer(modifier = Modifier.height(24.dp))

                // White Card for Reports
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                ) {
                    Text(
                        "Lacak Laporanmu!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Reports List
                    if (laporanSaya.isEmpty()) {
                        Text(
                            text = "Anda belum memiliki laporan.",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp),
                            textAlign = TextAlign.Center,
                            color = Color.Gray
                        )
                    } else {
                        laporanSaya.forEachIndexed { index, laporan ->
                            val (displayText, backgroundColor) = when (laporan.status) {
                                "Menunggu persetujuan" -> "Menunggu" to Color(0xFFFDBF11)
                                "Berhasil diunggah" -> "Disetujui" to Color(0xFF22C55E)
                                "Ditolak" -> "Ditolak" to Color(0xFFEF4444)
                                else -> laporan.status to Color.Gray
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = laporan.judul,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "${laporan.tanggal}  ${laporan.waktu}",
                                        fontSize = 12.sp,
                                        color = Color.Gray
                                    )
                                }

                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(50))
                                        .background(backgroundColor)
                                ) {
                                    Text(
                                        text = displayText,
                                        color = Color.White,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                                    )
                                }
                            }
                            if (index < laporanSaya.size - 1) {
                                Divider(
                                    color = Color.LightGray.copy(alpha = 0.5f),
                                    thickness = 1.dp,
                                    modifier = Modifier.padding(vertical = 16.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Logout Button
                Button(
                    onClick = {
                        FirebaseAuth.getInstance().signOut()
                        // Navigate to login screen, ensuring the back stack is cleared
                        navController.navigate(Routes.Login) {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC41532)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .height(48.dp)
                ) {
                    Text(
                        text = "Log Out",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }


        // Bottom dashboard - Now correctly placed inside the root Box
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
                    .fillMaxWidth()
                    .height(88.dp)
                    .offset(y = 8.dp)
                    .pointerInput(Unit) {}
            )

            // Row for navigation icons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .offset(
                            y = (-16).dp, x = (-72).dp
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.home_gray_png),
                        contentDescription = "Home button",
                        modifier = Modifier
                            .width(32.dp)
                            .height(32.dp)
                            .offset(x = 16.dp, y = 24.dp)
                            .clickable{
                                navController.navigate("dashboard")
                            }                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .offset(y = (-24).dp, x = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.note_gray),
                        contentDescription = "Edit button",
                        modifier = Modifier
                            .width(32.dp)
                            .height(32.dp)
                            .offset(y = 32.dp, x = (-32).dp)
                            .clickable {
                                navController.navigate("laporSigma1")
                            }
                    )

                }

                // Floating button for calls
                Column(
                    modifier = Modifier
                        .offset(y = (-8).dp),
                    Arrangement.Center
                ) {
                    Button(modifier = Modifier
                        .width(64.dp)
                        .height(64.dp),
                        shape = CircleShape,
                        contentPadding = PaddingValues(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0XFFBF002E)),
                        onClick = {
                            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                                ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED
                            ) {
                                // Permissions alsama ready granted, get the location
                                getUserLocation(context, navController)
                            } else {
                                // Request both permissions
                                permissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE))
                            }
                        }
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.phone_call_white),
                            contentDescription = "Call SIGMA",
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp),
                            Alignment.Center
                        )
                    }

                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .offset(y = (-16).dp, x = (-8).dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.book_gray),
                        contentDescription = "Edit button",
                        modifier = Modifier
                            .width(32.dp)
                            .height(32.dp)
                            .offset(y = 24.dp, x = 32.dp)
                            .clickable {
                                navController.navigate("BeritaTerkini") {
                                }
                            }
                    )

                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.offset(y = (-24).dp, x = 72.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.user_circle_red),
                        contentDescription = "Profile button",
                        modifier = Modifier
                            .width(32.dp)
                            .height(32.dp)
                            .offset(x = (-24).dp, y = (32).dp)

                    )

                }


            }
        }
    }
}

