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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.login.NewsViewModel
import com.example.login.R

@Composable
fun BeritaDetail(newsId: String, viewModel: NewsViewModel = viewModel(), navController: NavController) {
    val newsItem by viewModel.newsItem.observeAsState()

    LaunchedEffect(newsId) {
        viewModel.getNewsById(newsId)
    }

    if (newsItem != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0XFFF7EAEB))
        ) {
            // Content Column (scrollable)
            Column(
                modifier = Modifier
                    .weight(1f) // Takes up remaining space
                    .padding(16.dp, top = 50.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = newsItem!!.title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 24.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                // tags
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        newsItem!!.author,
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0XFFb42c38)),
                        color = Color.White,
                        fontSize = 12.sp
                    )
                    Text(
                        newsItem!!.date,
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0XFFb42c38)),
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // news image
                if (newsItem!!.imageUrl.isNotEmpty()) {
                    AsyncImage(
                         model = (newsItem!!.imageUrl),
                        contentDescription = "News Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                // news content
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = newsItem!!.description,
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // CTA button
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0XFFD61C4E)),
                        modifier = Modifier.fillMaxWidth(0.9f)
                    ) {
                        Text("Lapor disini!", color = Color.White, fontSize = 14.sp)
                    }
                }
            }

            // Bottom Dashboard (Navigation)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.White) // Use a solid background color
            ) {
                    // Row for navigation icons
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .height(82.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Home button
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.home),
                                contentDescription = "Home button",
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp)
                            )
                            Text(
                                "Beranda",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFFC35660)
                            )
                        }

                        // Lapor button
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.note_gray),
                                contentDescription = "Edit button",
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp)
                                    .clickable {
                                        navController.navigate("BeritaTerkini") {
                                            launchSingleTop = true
                                        }
                                    }
                            )
                            Text(
                                "Lapor",
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF616161),
                                fontSize = 13.sp
                            )
                        }

                        // Floating button for calls
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(
                                modifier = Modifier
                                    .width(60.dp)
                                    .height(60.dp),
                                shape = CircleShape,
                                contentPadding = PaddingValues(8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF431B3B)),
                                onClick = {}
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.phone_call_white),
                                    contentDescription = "Call SIGMA",
                                    modifier = Modifier
                                        .width(34.dp)
                                        .height(33.dp)
                                )
                            }
                            Text(
                                text = "Darurat",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0XFF616161),
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }

                        // Berita button
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.book_gray),
                                contentDescription = "Edit button",
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp)
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
                                fontWeight = FontWeight.Medium
                            )
                        }

                        // Profil button
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.user_circle),
                                contentDescription = "Profile button",
                                modifier = Modifier
                                    .width(36.dp)
                                    .height(36.dp)
                            )
                            Text(
                                text = "Profil",
                                color = Color(0xFF616161),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        } else {
        Text(text = "News not found", style = MaterialTheme.typography.headlineSmall)
    }
}


