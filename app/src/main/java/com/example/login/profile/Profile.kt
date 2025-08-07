import android.util.Log
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.login.NewsViewModel
import com.example.login.R
import com.example.login.Routes
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Profile(navController: NavController, viewModel: NewsViewModel = viewModel()) {

    // --- DATA LOGIC (UNCHANGED) ---
    val newsList by viewModel.newsList.collectAsState()
    val currentUid = FirebaseAuth.getInstance().currentUser?.uid
    val laporanSaya = newsList.filter { it.uid == currentUid }

    var userName by remember { mutableStateOf("Loading...") }
    var userEmail by remember { mutableStateOf("Loading...") }

    LaunchedEffect(Unit) {
        FirebaseAuth.getInstance().currentUser?.let { user ->
            userName = user.displayName ?: "Diandra Salim" // Placeholder for visual preview
            userEmail = user.email ?: "diandrasalim@gmail.com" // Placeholder for visual preview
        }
    }

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
                    .offset(y = (-100).dp), // Pulls this section up to overlap the header
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
                            .padding(top = 50.dp) // Half of image size (100dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.White)
                            .padding(top = 66.dp, start = 16.dp, end = 16.dp, bottom = 16.dp), // Padding inside the card
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = userName,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = userEmail,
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
                    Image(
                        painter = painterResource(id = R.drawable.profile_picture_image),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .border(4.dp, Color.White, CircleShape)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // White Card for Reports
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .padding(horizontal = 16.dp, vertical = 20.dp)
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


        // --- BOTTOM NAVIGATION BAR ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color.White)
                .height(80.dp)
                .border(width = 1.dp, color = Color.LightGray.copy(alpha = 0.5f))
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Navigation Items
                BottomNavItem(
                    iconRes = R.drawable.home_gray_png,
                    label = "",
                    isSelected = false,
                    onClick = { navController.navigate("Dashboard") }
                )
                BottomNavItem(
                    iconRes = R.drawable.note_gray,
                    label = "",
                    isSelected = false,
                    onClick = { navController.navigate(Routes.LaporSigma1) }
                )

                // Central Floating Action Button (Placeholder)
                Box(modifier = Modifier.size(60.dp))

                BottomNavItem(
                    iconRes = R.drawable.book_gray,
                    label = "",
                    isSelected = false,
                    onClick = { navController.navigate("BeritaTerkini") }
                )
                BottomNavItem(
                    iconRes = R.drawable.user_circle_red,
                    label = "",
                    isSelected = true,
                    onClick = { /* Already here */ }
                )
            }
        }

        // Floating Action Button (Emergency Call)
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(64.dp)
                .offset(y = (-40).dp), // Adjust position to be half-in, half-out
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC41532)), // Changed color to red
            onClick = { /* taruh navigasi call disini */ }
        ) {
            Image(
                painter = painterResource(id = R.drawable.phone_call_white),
                contentDescription = "Call SIGMA",
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun RowScope.BottomNavItem(iconRes: Int, label: String, isSelected: Boolean, onClick: () -> Unit) {
    val contentColor = if (isSelected) Color(0xFFC35660) else Color.Gray

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .weight(1f)
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            color = contentColor,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium
        )
    }
}
