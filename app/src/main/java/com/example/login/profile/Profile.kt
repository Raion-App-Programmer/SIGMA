package com.example.login.profile

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
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.login.ProfileViewModel
import com.example.login.R
import com.example.login.Routes
import com.example.mytestsigma.ui.theme.getUserLocation
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Profile(navController: NavController, viewModel: ProfileViewModel = viewModel()) {
    val nama = viewModel.nama
    val email = viewModel.email
    val context = LocalContext.current
    val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION

    fun logoutUser(navController: NavController) {
        FirebaseAuth.getInstance().signOut()
        navController.navigate("loginMasuk")
    }

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

    LaunchedEffect(Unit) {
        viewModel.loadData()
        val currentUser = FirebaseAuth.getInstance().currentUser
        val isEmailVerified = currentUser?.isEmailVerified ?: false
        Log.d("ProfileScreen", "Current User Email Verified: $isEmailVerified")
    }

    // Box utama untuk menumpuk konten yang bisa di-scroll dan navbar yang tetap.
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0XFFF5F5F5))
    ) {
        // Column ini membungkus SEMUA konten yang ingin digulir (scrollable).
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                // Beri padding di bagian bawah agar item terakhir (tombol logout) tidak tertutup navbar.
                .padding(bottom = 110.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Box untuk Header Merah dan Kartu Profil yang tumpang tindih
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                // 1. Latar Belakang Header Merah (dengan sudut lengkung)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp) // Tinggi disesuaikan
                        .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
                        .background(color = Color(0xFFBF002E)),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 56.dp, start = 24.dp, end = 24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.size(24.dp)) // Spacer untuk menyeimbangkan judul
                        Text(
                            text = "Profil",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Image(
                            painter = painterResource(id = R.drawable.gear_settings),
                            contentDescription = "Gear Settings",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    // Aksi klik untuk settings
                                }
                        )
                    }
                }

                // 2. Kartu Putih untuk Informasi Profil
                Box(
                    modifier = Modifier
                        .padding(top = 130.dp) // Jarak dari atas disesuaikan
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth()
                        .shadow(elevation = 4.dp, shape = RoundedCornerShape(20.dp))
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 60.dp, bottom = 20.dp, start = 20.dp, end = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Diandra Salim", // Menggunakan data statis sesuai gambar
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "diandrasalim@gmail.com", // Menggunakan data statis sesuai gambar
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { navController.navigate(Routes.UbahProfile) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC8102E))
                        ) {
                            Text("Ubah Profil", color = Color.White, fontSize = 14.sp)
                        }
                    }
                }

                // 3. Gambar Profil (di lapisan paling atas)
                Box(
                    modifier = Modifier
                        .padding(top = 80.dp) // Jarak dari atas disesuaikan
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(4.dp, color = Color.White, CircleShape)
                        .background(Color.Gray), // Fallback background
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_picture_image),
                        contentDescription = "Profile Picture",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            // Kartu untuk daftar Laporan
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    "Lacak Laporanmu!",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .align(Alignment.CenterHorizontally)
                )

                val reportItems = listOf(
                    Triple("Tabrakan Ijen", "Sabtu, 8 Maret 2025", "11:13 WIB" to "Menunggu"),
                    Triple("Suhat Banjir Terus, Rek.", "Kamis, 6 Maret 2025", "14:26 WIB" to "Disetujui"),
                    Triple("Konslet Listrik", "Senin, 3 Februari 2025", "22:04 WIB" to "Ditolak"),
                    Triple("Laka Lantas di Veteran", "Rabu, 22 Januari 2025", "08:34 WIB" to "Disetujui"),
                    Triple("Pohon Jatuh, Hati-Hati", "Jumat, 17 Januari 2025", "17:26 WIB" to "Disetujui")
                )

                reportItems.forEachIndexed { index, (title, date, statusInfo) ->
                    val (time, status) = statusInfo
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(title, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(date, fontSize = 12.sp, color = Color.Gray)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(time, fontSize = 12.sp, color = Color.DarkGray)
                                }
                            }

                            // Status Badge
                            val statusColor = when (status) {
                                "Menunggu" -> Color(0xFFE0A800)
                                "Disetujui" -> Color(0xFF28A745)
                                "Ditolak" -> Color(0xFFC8102E)
                                else -> Color.Gray
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50)) // Bentuk pil
                                    .background(statusColor)
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(text = status, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                            }
                        }
                        if (index < reportItems.lastIndex) {
                            Divider(color = Color(0xFFF2F2F2), thickness = 1.dp)
                        }
                    }
                }
            }

            // Tombol Log Out
            Button(
                onClick = { logoutUser(navController) },
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 24.dp)
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC8102E))
            ) {
                Text("Log Out", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        } // Akhir dari Column yang bisa di-scroll


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
                    .width(412.dp)
                    .height(100.dp)
                    .offset(y = 10.dp)
                    .pointerInput(Unit) {}
            )

            // Row for navigation icons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(82.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = CenterVertically
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
                            .clickable{
                                navController.navigate("Dashboard")
                            }
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
                                navController.navigate("laporSigma1")
                            }
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
                        .offset(y = (-15).dp, x = (-10).dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.book_gray),
                        contentDescription = "Edit button",
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .offset(y = 25.dp, x = 30.dp)
                            .clickable {
                                navController.navigate("BeritaTerkini") {
                                }
                            }
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
                            .width(30.dp)
                            .height(30.dp)
                            .offset(x = (-20).dp, y = (30).dp)

                    )

                }


            }
        }
    }
}
