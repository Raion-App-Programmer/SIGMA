package com.example.login.fitur_profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ubahProfile(){

    var nama by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var kataSandi by remember { mutableStateOf("") }
    var telepon by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }

    val dark_grey = colorResource(id = R.color.dark_grey)
    val dark0_grey = colorResource(id = R.color.dark0_grey)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
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
                    .fillMaxWidth()
                    .height(244.dp)
                    .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFFC41532),
                                Color(0xFF431B3B)
                            )
                        )
                    ),

                ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top,
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 38.dp, end = 38.dp, top = 64.dp),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier
                                .height(24.dp)
                        )

                        Spacer(
                            modifier = Modifier
                                .width(91.dp)
                        )

                        Text(
                            modifier = Modifier
                                .width(106.dp)
                                .height(25.dp),
                            text = "Ubah Profil",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Spacer(
                            modifier = Modifier
                                .width(91.dp)
                        )

                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "setting",
                            tint = Color.White,
                            modifier = Modifier
                                .height(24.dp)
                                .width(24.dp)
                                .clickable { }
                        )

                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .width(371.dp)
                        .height(530.dp)
                        .padding(start = 21.dp, end = 20.dp)
                        .offset(y = -69.dp),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 51.dp, end = 52.dp, top = 73.dp),
                        horizontalAlignment = Alignment.Start
                    ){

                        Text(
                         text = "Nama",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(700),
                            color = Color.Black,
                        )

                        Spacer(
                            modifier = Modifier
                                .height(3.dp)
                        )

                        OutlinedTextField(
                            value = nama,
                            onValueChange = { nama = it },
                            placeholder = { Text("Nama", color = dark_grey)
                                          },
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = dark0_grey, // Warna border saat fokus
                                unfocusedBorderColor = dark0_grey,
                            )
                        )

                        Spacer(
                            modifier = Modifier
                                .height(10.dp)
                        )

                        Text(
                            text = "Email",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(700),
                            color = Color.Black,
                        )

                        Spacer(
                            modifier = Modifier
                                .height(3.dp)
                        )

                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            placeholder = { Text("Email", color = dark_grey)
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = dark0_grey, // Warna border saat fokus
                                unfocusedBorderColor = dark0_grey,
                            )
                        )

                        Spacer(
                            modifier = Modifier
                                .height(10.dp)
                        )

                        Text(
                            text = "Kata Sandi",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(700),
                            color = Color.Black,
                        )

                        Spacer(
                            modifier = Modifier
                                .height(3.dp)
                        )

                        OutlinedTextField(
                            value = kataSandi,
                            onValueChange = { kataSandi = it },
                            placeholder = { Text("Kata Sandi", color = dark_grey)
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = dark0_grey, // Warna border saat fokus
                                unfocusedBorderColor = dark0_grey,
                            )
                        )

                        Spacer(
                            modifier = Modifier
                                .height(10.dp)
                        )

                        Text(
                            text = "Nomor Telepon",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(700),
                            color = Color.Black,
                        )

                        Spacer(
                            modifier = Modifier
                                .height(5.dp)
                        )

                        OutlinedTextField(
                            value = telepon,
                            onValueChange = { telepon = it },
                            placeholder = { Text("Nomor Telepon", color = dark_grey)
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = dark0_grey, // Warna border saat fokus
                                unfocusedBorderColor = dark0_grey,
                            )
                        )

                        Spacer(
                            modifier = Modifier
                                .height(10.dp)
                        )

                        Text(
                            text = "Alamat",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(700),
                            color = Color.Black,
                        )

                        Spacer(
                            modifier = Modifier
                                .height(3.dp)
                        )

                        OutlinedTextField(
                            value = alamat,
                            onValueChange = { alamat = it },
                            placeholder = { Text("Alamat", color = dark_grey)
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = dark0_grey, // Warna border saat fokus
                                unfocusedBorderColor = dark0_grey,
                            )
                        )

                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 115.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            Button(
                onClick = { },
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF431B3B),
                                    Color(0xFFC41532)
                                )
                            ),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Simpan",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}
