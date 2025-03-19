package com.example.mytestsigma.ui.theme



import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.login.R
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.IconButton
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.login.AuthViewModel
import com.example.login.NewsCard
import com.example.login.NewsViewModel
import com.example.login.Routes
import com.example.login.Routes.Profile
import com.example.login.fitur_panduan.PanduanBanjir
import okhttp3.Route

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Dashboard(navController: NavController , viewModel: NewsViewModel = viewModel()) {
    val newsList by viewModel.newsList.collectAsState()

    Box(
        Modifier
            .fillMaxSize()
            .background(
                color = Color(0XFFF7EAEB)
            )
    ) {
        // NavBar Rectangle at the Top
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 20.dp,
                        bottomEnd = 20.dp
                    )
                )
                .fillMaxWidth()
                .height(120.dp)
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFC41532),
                            Color(0xFF431B3B)
                        )
                    )
                ),
        ) {
            // To control profile - notification on top and weather - location on bottom
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // profile - notification on top
                Row(
                    modifier = Modifier
                ) {

                    Text(
                        text = "Halo, Diandra!",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .offset(x = 35.dp, y = 40.dp)
                    )


                    Image(
                        painter = painterResource(id = R.drawable.notifications),
                        contentDescription = "Notifications",
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .offset(x = 230.dp, y = 30.dp)
                    )
                }

                // weather - location
                Row(
                    modifier = Modifier
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.cloud),
                        contentDescription = "Weather",
                        modifier = Modifier
                            .width(32.dp)
                            .height(89.dp)
                            .offset(x = 35.dp, y = 12.dp)
                    )

                    Column(
                        modifier = Modifier
                            .offset(x = (-10.dp))
                    ) {
                        Text(
                            "Cerah",
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .offset(y = 40.dp, x = 52.dp)
                        )
                        Text(
                            "29° C", fontSize = 12.sp, color = Color.White,
                            modifier = Modifier.offset(y = 40.dp, x = 53.dp)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .offset(x = 73.dp, y = 40.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            "Jalan Kebangsaan Timur No. 10",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.offset(x = 30.dp, y = 3.dp)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.location_on),
                            contentDescription = "Location",
                            modifier = Modifier
                                .size(30.dp)
                                .offset(x = 30.dp)
                        )
                    }
                }
            }
        }

        // pager
        MyPagerWithDots()

        // Panduan Darurat
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = 40.dp, y = 200.dp)
        ) {
            Text(
                "Panduan Darurat",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(bottom = 15.dp)
                    .offset(y = (-225).dp)
            )

            // Panduan darurat container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-230).dp)
        ) {

                // Icons for Panduan Darurat
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .offset(x = (-40).dp, y = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .offset(y = (-10).dp)
                    ) {
                            Image(painter = painterResource(R.drawable.banjir_darurat),
                                contentDescription = "Banjir darurat button", modifier = Modifier
                                    .clickable { navController.navigate(Routes.PanduanBanjir) }
                                    .width(70.dp)
                                    .height(80.dp)
                                    )


                        Text(
                            "Banjir",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 6.dp)
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .offset(y = (-10).dp)

                    ) {
                        Box( modifier = Modifier.clickable { navController.navigate(Routes.PanduanBanjir) }) {
                            Image(
                                painter = painterResource(R.drawable.kebakaran_darurat),
                                contentDescription = "Kebakaran darurat button", modifier = Modifier
                                    .width(70.dp)
                                    .height(80.dp)
                            )
                        }
                        Text(
                            "Kebakaran",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(top = 6.dp)
                                .clickable { navController.navigate(Routes.PanduanKebakaran)}
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .offset(y = (-10).dp)
                    ) {
                        Image(painter = painterResource(id = R.drawable.gempa_darurat),
                            contentDescription = "Gempa darurat png", modifier = Modifier
                                .width(70.dp)
                                .height(80.dp))

                            // bikin route

                        Text(
                            "Gempa",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 6.dp)
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .offset(y = (-10).dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.p3k_darurat),
                            contentDescription = "P3K darurat",
                            modifier = Modifier
                                .width(70.dp)
                                .height(80.dp)
                        )

                        Text(
                            "P3K",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 6.dp)
                        )
                    }
                }
            }
        }

        // Column for Berita Terkini
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Berita Terkini",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .offset(y = 500.dp, x = ((-95).dp))
            )

            LazyRow (modifier = Modifier.fillMaxSize().padding(start = 20.dp)) {
                items(newsList) { newsItem ->
                    NewsCard(
                        imageUrl = newsItem.imageUrl,
                        date = newsItem.date,
                        title = newsItem.title,
                        author = newsItem.author,
                        onClick = {
                            navController.navigate("BeritaDetail/${newsItem.id}")
                        },
                        modifier = Modifier
                            .width(160.dp)
                            .height(240.dp)
                            .offset(x = 50.dp, y = 100.dp)
                    )
                    Log.d("newsitem.imageurl", newsItem.imageUrl)
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }

        // Bottom dashboard
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
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
                        painter = painterResource(id = R.drawable.book_gray),
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



@Composable
fun NewsCard(
    imageUrl: String,
    date: String,
    title: String,
    author: String,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = Modifier
            .width(140.dp)
            .height(260.dp)
            .offset(x = 16.dp, y = 520.dp)
            .clip(RoundedCornerShape(20.dp))
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
                .clip(RoundedCornerShape(20.dp))
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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyPagerWithDots() {
    val pageCount = 3 // Set number of pages
    val pagerState = rememberPagerState(pageCount = { pageCount })

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = 125.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Pager (Scrollable)
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .width(350.dp)
                .height(170.dp)
        ) { page ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(
                        id = when (page) {
                            0 -> R.drawable.lapor_segala_insiden_warna
                            1 -> R.drawable.lapor_segala_insiden_warna
                            else -> R.drawable.lapor_segala_insiden_warna
                        }
                    ),
                    contentDescription = "Page $page", // Add contentDescription
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop // Add contentScale
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Dot Indicator
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(pageCount) { index ->
                Box(
                    modifier = Modifier
                        .size(if (pagerState.currentPage == index) 12.dp else 8.dp)
                        .clip(CircleShape)
                        .background(if (pagerState.currentPage == index) Color.Black else Color.Gray)
                        .padding(5.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
        }
    }
}

@Preview
@Composable
fun DashboardPreview() {
    val navController = rememberNavController()
    Dashboard(navController)
}

