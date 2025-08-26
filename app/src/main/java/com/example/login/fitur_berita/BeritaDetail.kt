import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Paint.Align
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.login.NewsViewModel
import com.example.login.R
import com.example.login.Routes
import com.example.mytestsigma.ui.theme.getUserLocation
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyRow

@Composable
fun BeritaDetail(newsId: String, viewModel: NewsViewModel = viewModel(), navController: NavController) {
    val newsItem by viewModel.newsItem.observeAsState()
    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true && permissions[Manifest.permission.CALL_PHONE] == true) {
            Toast.makeText(context, "Izin lokasi dan panggilan diberikan", Toast.LENGTH_SHORT).show()
            getUserLocation(context, navController)
        } else if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true) {
            Toast.makeText(context, "Izin lokasi diberikan, izin panggilan ditolak", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Izin lokasi ditolak", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(newsId) {
        viewModel.getNewsById(newsId)
    }

    if (newsItem != null) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Arrow back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = newsItem!!.judul,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                val imageUrls = newsItem?.buktiUrls ?: newsItem?.buktiUrl?.let { listOf(it) } ?: emptyList()

                if (imageUrls.isNotEmpty()) {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(imageUrls) { url ->
                            AsyncImage(
                                model = url,
                                contentDescription = "Gambar Bukti",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(width = 240.dp, height = 200.dp)
                                    .clip(RoundedCornerShape(16.dp))
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                } else {
                    AsyncImage(
                        model = newsItem!!.buktiUrl,
                        contentDescription = "News Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(224.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                }


                Log.d("List",newsItem!!.buktiUrl)

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "${newsItem!!.tanggal} • ${newsItem!!.waktu}",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = newsItem!!.nama,
                            color = Color.Gray,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            painter = painterResource(R.drawable.user_circle),
                            contentDescription = "User",
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = newsItem!!.deskripsi,
                    fontSize = 16.sp,
                    color = Color.Black,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                Column(){
                    Text(
                        text = "Apakah Anda sedang dalam situasi seperti berita?",
                        color = Color.Black,
                        fontSize = 14.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Button(
                        onClick = { navController.navigate(Routes.LaporSigma1) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0XFFBF002E)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Lapor disini!", color = Color.White, fontSize = 14.sp)
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // Bottom navigation bar background
                Image(
                    painter = painterResource(id = R.drawable.rectangle_bottom_dashboard_colored),
                    contentDescription = "Dashboard navigation bottom",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(88.dp)
                        .offset(y = 8.dp)
                        .pointerInput(Unit) {}
                )

                // Row for navigation icons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = CenterVertically
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .offset(
                                y = (-16).dp, x = (-72).dp
                            )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.home_gray_png),
                            contentDescription = "Home button",
                            modifier = Modifier
                                .width(32.dp)
                                .height(32.dp)
                                .offset(x = 16.dp, y = 24.dp)
                                .clickable{
                                    navController.navigate("dashboard")
                                }                    )
                    }

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .offset(y = (-24).dp, x = 8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.note_gray),
                            contentDescription = "Edit button",
                            modifier = Modifier
                                .width(32.dp)
                                .height(32.dp)
                                .offset(y = 32.dp, x = (-32).dp)
                                .clickable {
                                    navController.navigate("laporSigma1")
                                }
                        )

                    }

                    // Floating button for calls
                    Column(
                        modifier = Modifier
                            .offset(y = (-8).dp),
                        Arrangement.Center
                    ) {
                        Button(modifier = Modifier
                            .width(60.dp)
                            .height(60.dp),
                            shape = CircleShape,
                            contentPadding = PaddingValues(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0XFFBF002E)),
                            onClick = {
                                if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                                    ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED
                                ) {
                                    // Permissions alsama ready granted, get the location
                                    getUserLocation(context, navController)
                                } else {
                                    // Request both permissions
                                    permissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE))
                                }
                            }
                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.phone_call_white),
                                contentDescription = "Call SIGMA",
                                modifier = Modifier
                                    .width(40.dp)
                                    .height(40.dp),
                                Alignment.Center
                            )
                        }

                    }

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .offset(y = (-16).dp, x = (-8).dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.book_red),
                            contentDescription = "Edit button",
                            modifier = Modifier
                                .width(32.dp)
                                .height(32.dp)
                                .offset(y = 24.dp, x = 32.dp)

                        )

                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.offset(y = (-24).dp, x = 72.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.user_circle),
                            contentDescription = "Profile button",
                            modifier = Modifier
                                .width(32.dp)
                                .height(32.dp)
                                .offset(x = (-24).dp, y = (32).dp)
                                .clickable {
                                    navController.navigate(Routes.Profile)
                                }
                        )

                    }


                }
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "News not found", style = MaterialTheme.typography.bodyLarge)
        }
    }
}



