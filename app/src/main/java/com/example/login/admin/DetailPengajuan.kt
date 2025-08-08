package com.example.login.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.login.NewsViewModel
import com.example.login.ui.theme.LoginTheme
@Composable
fun DetailPengajuanScreen(
    newsId: String,
    navController: NavController,
    viewModel: NewsViewModel = viewModel()
) {
    val newsList by viewModel.newsList.collectAsState()
    val laporan = newsList.find { it.id == newsId }

    if (laporan == null) {
        Text("Laporan tidak ditemukan", modifier = Modifier.padding(16.dp))
        return
    }

    // Gunakan data `laporan` di layout-mu
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .clickable { navController.popBackStack() }
                .padding(top = 20.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        val imageUrls = laporan?.buktiUrls ?: laporan?.buktiUrl?.let { listOf(it) } ?: emptyList()

        if (imageUrls.isNotEmpty()) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(imageUrls) { url ->
                    AsyncImage(
                        model = url,
                        contentDescription = "Gambar Bukti",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(width = 250.dp, height = 200.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        } else if (laporan.buktiUrl.isNotEmpty()){
            AsyncImage(
                model = laporan!!.buktiUrl,
                contentDescription = "News Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Gray)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = laporan.judul,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = laporan.waktu,
            color = Color.Black,
            fontWeight = FontWeight.Light,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(15.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TagChip(text = laporan.nama, background = Color(0xFFD32F2F))
            TagChip(text = laporan.tanggal, background = Color(0xFFD32F2F))
            TagChip(text = laporan.waktu, background = Color(0xFFD32F2F))
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = laporan.deskripsi,
            fontSize = 16.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

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
                    Button(
                        onClick = {
                            viewModel.updateStatus(newsId, "Menunggu persetujuan")
                            navController.popBackStack() },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF33B249)),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(36.dp),
                    ) {
                        Text(
                            "Mnunggu Knfrm",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(
                        onClick = {
                            viewModel.updateStatus(newsId, "Berhasil diunggah")
                            navController.popBackStack() },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF33B249)),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(36.dp),
                    ) {
                        Text(
                            "Setujui",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Button(
                        onClick = {
                            viewModel.updateStatus(newsId, "Ditolak")
                            navController.popBackStack() },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFC41532)),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(36.dp),
                    ) {
                        Text(
                            "Tolak",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
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
            Text(
                text = text,
                color = Color.White,
                fontSize = 12.sp,
                overflow = TextOverflow.Ellipsis
            )
        }
    }


