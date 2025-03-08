package com.example.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun panggilSigma1(){
    val backgroundColor = colorResource(id = R.color.bg_panggilsigma)
    Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
                .background(
                    color = backgroundColor
                )

        )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .height(145.dp)
                .shadow(
                    elevation = 100.dp, // Tinggi bayangan
                    shape = RoundedCornerShape(
                        bottomStart = 45.dp,
                        bottomEnd = 45.dp
                    ), // Sudut membulat
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        bottomStart = 45.dp,
                        bottomEnd = 45.dp
                    ) // Harus sama dengan shadow agar bentuknya pas
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Hanya Untuk Darurat",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = colorResource(id = R.color.font_hanyaUntuDarurat),
                textAlign = TextAlign.Center
            )
        }

        Spacer(
            modifier = Modifier
                .height(35.dp)
        )

        Text(
            text = "Malang Kota",
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier
                .height(35.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { },
                modifier = Modifier
                    .width(190.dp)
                    .height(240.dp)
                    .background(color = Color.Transparent),
                shape = RoundedCornerShape(20.dp),
                contentPadding = PaddingValues()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = colorResource(id = R.color.bg_buttonPanggil),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Masuk", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(
                modifier = Modifier
                    .width(10.dp)
            )

                Button(
                    onClick = { },
                    modifier = Modifier
                        .width(190.dp)
                        .height(240.dp)
                        .background(color = Color.Transparent),
                    shape = RoundedCornerShape(20.dp),
                    contentPadding = PaddingValues()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = colorResource(id = R.color.bg_buttonPanggil),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Masuk", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

        Spacer(
            modifier = Modifier
                .height(15.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { },
                modifier = Modifier
                    .width(190.dp)
                    .height(240.dp)
                    .background(color = Color.Transparent),
                shape = RoundedCornerShape(20.dp),
                contentPadding = PaddingValues()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = colorResource(id = R.color.bg_buttonPanggil),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Masuk", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(
                modifier = Modifier
                    .width(10.dp)
            )

            Button(
                onClick = { },
                modifier = Modifier
                    .width(190.dp)
                    .height(240.dp)
                    .background(color = Color.Transparent),
                shape = RoundedCornerShape(20.dp),
                contentPadding = PaddingValues()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = colorResource(id = R.color.bg_buttonPanggil),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Masuk", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(82.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .offset(
                        y = (-15).dp
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home button",
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .offset(x = 15.dp, y = 25.dp)
                )
                Text(
                    "Beranda",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFC35660),
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
                )
                Text(
                    "Lapor",
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF616161),
                    fontSize = 13.sp,
                    modifier = Modifier
                        .offset(x = (-40).dp, y = 35.dp)
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
                    contentDescription = "Book button",
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .offset(y = 30.dp, x = 27.dp)
                )
                Text(
                    "Berita",
                    fontSize = 13.sp,
                    color = Color(0xFF616161),
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .offset(y = 28.dp, x = 28.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.offset(y = (-15).dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile button",
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .offset(x = (-20).dp, y = (30.dp))
                )
                Text(text = "Profil", color = Color(0xFF616161), fontSize = 13.sp, fontWeight = FontWeight.Medium, modifier = Modifier
                    .offset(x = (-20).dp, y = 30.dp))
            }
        }

        // Floating button for calls
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter as Alignment.Horizontal)
                .offset(y = (-10).dp)
        ) {
            Column {
                Image(painter = painterResource(id = R.drawable.circle_call),
                    contentDescription = "Circle call",
                    modifier = Modifier
                        .width(80.dp)
                        .height(78.dp))

                Text(text = "Darurat", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF616161), modifier = Modifier
                    .offset(y = (-4).dp, x = 21.dp))
            }
            Image(
                painter = painterResource(id = R.drawable.phone_call_white),
                contentDescription = "Call SIGMA",
                modifier = Modifier
                    .width(34.dp)
                    .height(33.dp)
                    .align(Alignment.Center)
                    .offset(y = (-10).dp)
            )
        }
    }

    }


