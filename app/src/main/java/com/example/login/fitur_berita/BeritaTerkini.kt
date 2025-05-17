package com.example.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.login.Routes.Profile
import okhttp3.Route

@Composable
fun BeritaTerkini(navController: NavController, viewModel: NewsViewModel = viewModel()) {
    val newsList by viewModel.newsList.collectAsState()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        Alignment.Center) {
        Column (modifier = Modifier.fillMaxSize().align(Alignment.Center)) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
                    .width(412.dp)
                    .height(119.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color(0XFFC41532),
                                Color(0XFF431B3B)
                            )
                        )
                    )
            ) {
                Text(
                    "Berita Hari Ini",
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700),
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn (modifier = Modifier.weight(1f).align(Alignment.CenterHorizontally)){
                items(newsList) { newsItem ->
                    NewsCard(
                        imageUrl = newsItem.buktiUrl,
                        date = newsItem.tanggal,
                        title = newsItem.judul,
                        author = newsItem.nama,
                        onClick = {
                            navController.navigate("BeritaDetail/${newsItem.id}")
                        }
                    )
                    Log.d("newsitem.imageurl", newsItem.buktiUrl)

                }
            }
            // Bottom dashboard
            Box(
                modifier = Modifier
                    .fillMaxWidth()
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
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF431B3B)),
                            onClick = {
                                // taruh navigasi call disini
                            }
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
                            painter = painterResource(id = R.drawable.book_red),
                            contentDescription = "Edit button",
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .offset(y = 30.dp, x = 27.dp)
                                .clickable {
                                    navController.navigate("BeritaTerkini") {
                                        launchSingleTop = true
                                    }
                                }
                        )
                        Text(
                            "Berita",
                            fontSize = 13.sp,
                            color = Color(0xFFC35660),
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
                            painter = painterResource(id = R.drawable.user_circle),
                            contentDescription = "Profile button",
                            modifier = Modifier
                                .width(36.dp)
                                .height(36.dp)
                                .offset(x = (-20).dp, y = (30.dp))
                                .clickable {
                                    navController.navigate(Profile)
                                }
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

@Composable
fun NewsCard(
    imageUrl: String,
    date: String,
    title: String,
    author: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(40.dp))
            .width(372.dp)
            .height(180.dp)
            .padding(bottom = 16.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center// Makes it clickable
    ) {
        // Background Image
        AsyncImage(
            model = imageUrl,
            contentDescription = "News Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
                .clip(RoundedCornerShape(40.dp))
        )

        // Semi-transparent Overlay
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(40.dp))
                .fillMaxSize()
                .background(brush = Brush.horizontalGradient(
                    listOf(
                        Color(0X99C41532),
                        Color(0X99431B3B),
                    )
                ))
        )

        // Text & Button Overlay
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Date Label
            Text(
                text = date,
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier
                    .background(Color(0x88FFFFFF), RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )

            // Title & Author
            Column (verticalArrangement = Arrangement.Bottom) {
                Text(text = title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = author, color = Color.White, fontSize = 12.sp)
            }

            // button selengkapnya
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.3f)),
                shape = RoundedCornerShape(50),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Selengkapnya", color = Color.White)
            }
        }
    }
}
