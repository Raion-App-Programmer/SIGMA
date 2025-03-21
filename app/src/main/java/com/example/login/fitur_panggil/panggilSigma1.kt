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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController



@Composable
fun panggilSigma1(navController: NavController){

    val backgroundColor = colorResource(id = R.color.bg_panggilsigma)
    val context = LocalContext.current
    fun dialNumber(number: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$number")
        }
        context.startActivity(intent)
    }
    Box(
            modifier = Modifier
                .fillMaxSize()
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
                .height(130.dp)
                .shadow(
                    elevation = 100.dp,
                    shape = RoundedCornerShape(
                        bottomStart = 45.dp,
                        bottomEnd = 45.dp
                    ),
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        bottomStart = 45.dp,
                        bottomEnd = 45.dp
                    )
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
                .height(30.dp)
        )

        Text(
            text = "Malang Kota",
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier
                .height(30.dp)
        )

            Button(
                onClick = { dialNumber("0341362222") },
                modifier = Modifier
                    .width(372.dp)
                    .height(125.dp)
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
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(start = 32.dp, end = 88.dp),
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = "Pemadam",
                                textAlign = TextAlign.Center,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Kebakaran",
                                textAlign = TextAlign.Center,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.mobil_damkar),
                                contentDescription = "damkar",
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier
                                    .width(174.dp)
                                    .height(92.dp)
                                    .offset(x = 16.dp, y = 5.dp)
                            )
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )

            Button(
                onClick = { dialNumber("119") },
                modifier = Modifier
                    .width(372.dp)
                    .height(125.dp)
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
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 32.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Ambulance",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ambulance),
                                contentDescription = "ambulance",
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier
                                    .width(172.dp)
                                    .height(172.dp)
                                    .offset(x = 20.dp, y = 13.dp)
                            )
                        }
                    }
                }
            }


        Spacer(
            modifier = Modifier
                .height(15.dp)
        )

            Button(
                onClick = { dialNumber("110") },
                modifier = Modifier
                    .width(372.dp)
                    .height(125.dp)
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
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 32.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Polisi",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.police),
                                contentDescription = "police",
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier
                                    .width(130.dp)
                                    .height(148.dp)
                                    .offset(x = 20.dp, y = 18.dp)
                            )
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )

            Button(
                onClick = { dialNumber("0341324018") },
                modifier = Modifier
                    .width(372.dp)
                    .height(125.dp)
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
                )
                {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 32.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "PMI",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.pmi),
                                contentDescription = "pmi",
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier
                                    .width(141.dp)
                                    .height(138.dp)
                                    .offset(x = 20.dp, y = 20.dp)
                            )
                        }
                    }
                }
            }

// Bottom dashboard
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
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
                        )
                        Text(
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

                    // Floating button for calls


                    Column(
                        modifier = Modifier
                            .offset(y = (-5).dp),
                        Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                // TODO: Tambahkan aksi klik
                            },
                            modifier = Modifier
                                .size(60.dp) // Menggunakan size untuk width & height sekaligus
                                .clip(CircleShape), // Memastikan bentuknya lingkaran
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF431B3B)),
                            contentPadding = PaddingValues(8.dp),
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
                        Text(
                            text = "Darurat",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFC35660),
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
                                .offset(y = 25.dp, x = 28.dp)
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.offset(y = (-20).dp, x = 70.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profil_icon),
                            contentDescription = "Profile button",
                            modifier = Modifier
                                .width(36.dp)
                                .height(36.dp)
                                .offset(x = (-20).dp, y = (30.dp))
                        )
                        Text(
                            text = "Profil",
                            color = Color(0xFF616161),
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
}



