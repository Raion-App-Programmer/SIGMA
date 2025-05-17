package com.example.login.lapor


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.login.R
import com.example.login.Routes
import com.example.login.fitur_lapor.LaporanViewModel
import com.example.login.fitur_lapor.buttomNavbarLapor


@Composable
fun laporSigma1(navController: NavController, laporanViewModel: LaporanViewModel){
    var errorMessage by remember { mutableStateOf("") }
    val dark_grey = colorResource(id = R.color.dark_grey)

    val context = LocalContext.current

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
                    .height(119.dp)
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
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
                            .width(30.dp)
                    )

                    Text(
                        modifier = Modifier
                            .width(122.dp)
                            .height(25.dp),
                        text = "Lapor Sigma",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(
                        modifier = Modifier
                            .width(150.dp)
                    )

                    Text(
                        text = "1/3",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .height(30.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Text(
                    text = "Nama Pelapor",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                TextField(
                    value = laporanViewModel.nama.value,
                    onValueChange = { laporanViewModel.nama.value = it  },
                    placeholder = { Text("Nama", color = dark_grey) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(18.dp),
                        )
                        .clip(RoundedCornerShape(18.dp))
                        .heightIn(max = 65.dp),
                            maxLines = Int.MAX_VALUE,

                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    )
                        )

                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )

                Text(
                    text = "Tanggal Kejadian",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                TextField(
                    value = laporanViewModel.tanggal.value,
                    onValueChange = { laporanViewModel.tanggal.value = it  },
                    placeholder = { Text("Tanggal Kejadian", color = dark_grey) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(18.dp),
                        )
                        .clip(RoundedCornerShape(18.dp))
                        .heightIn(max = 65.dp),
                    maxLines = Int.MAX_VALUE,

                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    )
                )
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )

                Text(
                    text = "Waktu Kejadian",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                TextField(
                    value = laporanViewModel.waktu.value,
                    onValueChange = { laporanViewModel.waktu.value = it  },
                    placeholder = { Text("Waktu Kejadian", color = dark_grey) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(18.dp),
                        )
                        .clip(RoundedCornerShape(18.dp))
                        .heightIn(max = 65.dp),
                    maxLines = Int.MAX_VALUE,

                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    )
                )
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )

                Text(
                    text = "Lokasi Kejadian",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                TextField(
                    value = laporanViewModel.lokasi.value,
                    onValueChange = { laporanViewModel.lokasi.value = it  },
                    placeholder = { Text("Lokasi Kejadian", color = dark_grey) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .height(100.dp)
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(18.dp),
                        )
                        .clip(RoundedCornerShape(18.dp))
                        .heightIn(max = 100.dp),
                    maxLines = Int.MAX_VALUE,

                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    )
                )

                Spacer(
                    modifier = Modifier
                        .height(154.dp)
                )
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp, start = 20.dp, end = 20.dp)
                    )
                }
                Button(
                    onClick = {
                        if (laporanViewModel.nama.value.isBlank() || laporanViewModel.tanggal.value.isBlank() || laporanViewModel.waktu.value.isBlank() || laporanViewModel.lokasi.value.isBlank()) {
                            Toast.makeText(context, "Isi dengan benar ya", Toast.LENGTH_SHORT).show()

                        } else {
                            errorMessage = ""
                            navController.navigate(Routes.LaporSigma2)
                        }},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .background(color = Color.Transparent),
                    shape = RoundedCornerShape(16.dp),
                    contentPadding = PaddingValues()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(0xFFC41532),
                                        Color(0xFF431B3B)
                                    )
                                ),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Selanjutnya",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }



            }

        }
        buttomNavbarLapor(navController)
    }
}
