package com.example.login.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.login.NewsItem
import com.example.login.NewsViewModel
import com.example.login.R
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotifikasiPage(navController: NavController, viewModel: NewsViewModel = viewModel()) {
    // Mengambil daftar laporan dari ViewModel dan memfilternya berdasarkan UID pengguna saat ini
    val newsList by viewModel.newsList.collectAsState()
    val currentUid = FirebaseAuth.getInstance().currentUser?.uid
    val laporanPengguna = newsList.filter { it.uid == currentUid }

    // Mengelompokkan laporan berdasarkan tanggal
    val groupedNotifications = laporanPengguna.groupBy { it.tanggal }
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Semua", "Hari ini")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Notifikasi Laporan",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFC41532)
                )
            )
        },
        containerColor = Color(0xFFF0F0F0)
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = Color(0xFFC41532),
                contentColor = Color.White,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = Color.White,
                        height = 3.dp
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title, fontWeight = FontWeight.Bold) }
                    )
                }
            }

            if (laporanPengguna.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Anda belum memiliki notifikasi laporan.",
                        color = Color.Gray
                    )
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    groupedNotifications.forEach { (date, notifications) ->
                        item {
                            Text(
                                text = date,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = Color.Gray,
                                modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
                            )
                        }
                        items(notifications) { laporan ->
                            NotificationItem(laporan)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NotificationItem(laporan: NewsItem) {
    // Menentukan warna status berdasarkan teks status
    val statusColor = when (laporan.status) {
        "Menunggu persetujuan" -> Color(0xFFFDBF11) // Kuning
        "Berhasil diunggah" -> Color(0xFF22C55E) // Hijau
        "Ditolak" -> Color(0xFFEF4444) // Merah
        else -> Color.Gray
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable { /* Handle click */ }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "Info Icon",
            tint = Color(0xFFC41532),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Status Laporan Anda",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = laporan.judul,
                fontSize = 14.sp,
                color = Color.DarkGray,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Status: ${laporan.status}",
                fontSize = 14.sp,
                color = statusColor
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            painter = androidx.compose.ui.res.painterResource(id = R.drawable.arrow_kiri),
            contentDescription = "Go to detail",
            tint = Color(0xFFC41532),
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NotifikasiPagePreview() {
    // Preview memerlukan NavController, jadi kita buat instance palsu
    NotifikasiPage(rememberNavController())
}
