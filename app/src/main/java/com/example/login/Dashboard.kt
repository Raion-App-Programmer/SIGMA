package com.example.mytestsigma.ui.theme

//import androidx.compose.material3.carousel.rememberCarouselState
//import coil.compose.HorizontalUncontainedCarousel


import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.layout.ContentScale
import com.example.login.Routes
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Dashboard(navController: NavController) {
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
            ) {val handleClick: (String) -> Unit = {route ->
                navController.navigate(route)
            }

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
                        IconButton(onClick = { handleClick(Routes.PanduanBanjir) }, modifier = Modifier
                            .width(70.dp)
                            .height(80.dp)
                        ) {
                            Image(painter = painterResource(R.drawable.banjir_darurat),
                                contentDescription = "Banjir darurat button", modifier = Modifier
                                    .width(70.dp)
                                    .height(80.dp))
                        }

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
                        IconButton(onClick = { handleClick(Routes.PanduanKebakaran) }, modifier = Modifier
                            .width(70.dp)
                            .height(80.dp)
                        ) {
                            Image(painter = painterResource(R.drawable.kebakaran_darurat),
                                contentDescription = "Kebakaran darurat button", modifier = Modifier
                                    .width(70.dp)
                                    .height(80.dp))
                        }

                        Text(
                            "Kebakaran",
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
                    .offset(y = 195.dp, x = ((-95).dp))
            )

            Spacer(modifier = Modifier.height((-2).dp))

            MyLazyRow(
                listOf(
                    Color(0xFFC41532),
                    Color(0xFF431B3B)
                )
            )
        }

        // Bottom dashboard
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 770.dp)
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
                                TODO()
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
                        painter = painterResource(id = R.drawable.user_circle),
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



@Composable
fun MyLazyRow(boxList: List<Color>) {
    Color.Gray
    LazyRow (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .offset(y = 200.dp, x = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(boxList) {
                color ->
            Box(
                modifier = Modifier
                    .width(152.dp)
                    .height(227.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFC41532), Color(0XFF5E0A18)
                            )
                        ), shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Box", color = Color.White)
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

