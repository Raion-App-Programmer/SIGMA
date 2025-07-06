package com.example.login.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.ui.theme.LoginTheme

@Composable
fun DetailPengajuanScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .clickable { /* Navigasi balik */ }
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Gray)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Hati – hati pasar comboran kebakaran dahsyat.",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Jalan Pasar Comboran",
            color = Color.Black,
            fontWeight = FontWeight.Light,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(15.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TagChip(text = "Raka", background = Color(0xFFD32F2F))
            TagChip(text = "Minggu, 2 Februari 2025", background = Color(0xFFD32F2F))
            TagChip(text = "20.46 WIB", background = Color(0xFFD32F2F))
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Saya melihat kebakaran besar di Pasar Comboran pada Minggu, 2 Februari 2025, sekitar pukul 20.46 WIB. Api terlihat sangat besar dan cepat menyebar ke beberapa bagian bangunan. Banyak warga yang panik dan berusaha menyelamatkan barang dagangan mereka. Petugas pemadam kebakaran sudah berada di lokasi, namun api masih sulit dikendalikan. Lalu lintas di sekitar pasar pun menjadi macet karena banyaknya warga yang berkerumun untuk melihat kejadian ini.",
            fontSize = 16.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(50.dp))

        Surface(
            shape = RoundedCornerShape(12.dp),
            elevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Status Pengajuan :", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(35.dp))
                    Box(
                        modifier = Modifier
                            .width(180.dp)
                            .height(40.dp)
                            .background(Color(0xFFFFA726), shape = RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Menunggu \nKonfirmasi",
                            color = Color.White,
                            fontSize = 16.sp,
                            lineHeight = 16.sp)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(
                        onClick = { /* Setujui */ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF33B249)),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(36.dp),
                    ) {
                        Text("Setujui", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = { /* Tolak */ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFC41532)),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(36.dp),
                    ) {
                        Text("Tolak", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

        @Composable
fun TagChip(text: String, background: Color) {
    Box(
        modifier = Modifier
            .background(background, shape = RoundedCornerShape(50))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(text = text, color = Color.White, fontSize = 12.sp, overflow = TextOverflow.Ellipsis)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailPengajuan() {
    LoginTheme {
        DetailPengajuanScreen()
    }
}