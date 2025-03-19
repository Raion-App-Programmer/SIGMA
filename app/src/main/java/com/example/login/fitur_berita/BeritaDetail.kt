import android.graphics.Paint.Align
import android.view.RoundedCorner
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
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
import com.example.login.NewsItem
import com.example.login.NewsViewModel
import com.example.login.R
import com.example.login.Routes
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

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
                        )
                    }


                }
            }
            }
        } else {
        Text(text = "News not found", style = MaterialTheme.typography.headlineSmall)
    }
}


