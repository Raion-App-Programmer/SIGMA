package com.example.login.fitur_panduan


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.login.R
import com.example.login.Routes

@Composable
fun buttomNavbarPanduan(navController: NavController){
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
                        painter = painterResource(id = R.drawable.home_black),
                        contentDescription = "Home button",
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .offset(x = 15.dp, y = 25.dp)
                            .clickable {
                                navController.navigate(Routes.Dashboard)
                            }

                    )
                    Text(
                        "Beranda",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF616161),
                        modifier = Modifier
                            .offset(x = 15.dp, y = 25.dp)
                            .clickable {
                                navController.navigate(Routes.Dashboard)
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
                            .clickable{
                                navController.navigate(Routes.LaporSigma1)
                            }
                    )
                    Text(
                        "Lapor",
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF616161),
                        fontSize = 13.sp,
                        modifier = Modifier
                            .offset(x = (-40).dp, y = 35.dp)
                            .clickable{
                                navController.navigate(Routes.LaporSigma1)
                            }
                    )
                }

                // Floating button for calls


                Column(
                    modifier = Modifier
                        .offset(y = (-5).dp),
                    Arrangement.Center
                ) {
                    Button(
                        onClick = { },
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
                        color = Color(0XFF616161),
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
                            .clickable {
                                navController.navigate(Routes.BeritaTerkini)
                            }
                    )
                    Text(
                        "Berita",
                        fontSize = 13.sp,
                        color = Color(0xFF616161),
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .offset(y = 25.dp, x = 28.dp)
                            .clickable {
                                navController.navigate(Routes.BeritaTerkini)
                            }
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
                            .clickable {
                                navController.navigate(Routes.Profile)
                            }
                    )
                    Text(
                        text = "Profil",
                        color = Color(0xFF616161),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .offset(x = (-20).dp, y = 30.dp)
                            .clickable {
                                navController.navigate(Routes.Profile)
                            }
                    )
                }


            }
        }
    }
}