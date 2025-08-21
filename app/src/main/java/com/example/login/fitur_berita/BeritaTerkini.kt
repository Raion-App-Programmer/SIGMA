package com.example.login


import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
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
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.login.Routes.Profile
import com.example.mytestsigma.ui.theme.getUserLocation


@Composable
fun BeritaTerkini(navController: NavController, viewModel: NewsViewModel = viewModel(), profileViewModel: ProfileViewModel = viewModel()) {
    val newsList by viewModel.newsList.collectAsState()
    val profileList by profileViewModel.profileList.collectAsState()


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

    val combinedList = newsList
        .filter { it.status == "Berhasil diunggah" }
        .map { news ->
            val thumbnailUrl = when {
                !news.buktiUrls.isNullOrEmpty() -> news.buktiUrls[0].toString()
                !news.buktiUrl.isNullOrEmpty() -> news.buktiUrl
                else -> null
            }

            val profile = profileList.find { it.id == news.uid }
            Triple(
                news.copy(buktiUrl = thumbnailUrl ?: "" ),
                profile?.nama ?: news.nama,
                profile?.buktiUrl // kalau tidak ada cocokannya otomatis null
            )
        }



    Box(modifier = Modifier
        .fillMaxSize(),
        Alignment.Center) {
        Column (modifier = Modifier
            .fillMaxSize()
            .align(Alignment.Center)) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
                    .width(412.dp)
                    .height(130.dp)
                    .background(
                        Color(0xFFBF002E),
                    ),
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

            // Gabungkan data


            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                items(combinedList) { (news, authorName, profileUrl) ->
                    NewsCard(
                        imageUrl = news.buktiUrl, // ini gambar berita
                        date = news.tanggal,
                        title = news.judul,
                        author = authorName,
                        profileUrl = profileUrl
                    ) {
                        navController.navigate("BeritaDetail/${news.id}")
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(),

            ) {
                // Bottom navigation bar background
                Image(
                    painter = painterResource(id = R.drawable.rectangle_bottom_dashboard_colored),
                    contentDescription = "Dashboard navigation bottom",
                    modifier = Modifier
                        .width(412.dp)
                        .height(100.dp)
                        .offset(y = 10.dp)
                        .pointerInput(Unit) {}
                )

                // Row for navigation icons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(82.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = CenterVertically
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
                                    navController.navigate("Dashboard") {
                                    }
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
                                .clickable {
                                    navController.navigate("laporSigma1")
                                }
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
                            .offset(y = (-15).dp, x = (-10).dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.book_red),
                            contentDescription = "Edit button",
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .offset(y = 25.dp, x = 30.dp)

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
                                .width(30.dp)
                                .height(30.dp)
                                .offset(x = (-20).dp, y = (30).dp)
                                .clickable {
                                    navController.navigate(Profile)
                                }
                        )

                    }


                }
            }
        }
    }
}


@Composable
fun NewsCard(
    imageUrl: String?,
    date: String,
    title: String,
    author: String,
    profileUrl: String?,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .width(372.dp)
            .height(180.dp)
            .padding(bottom = 16.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        // Background Image
        AsyncImage(
            model = imageUrl,
            contentDescription = "News Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp)),
            placeholder = painterResource(id = R.drawable.no_image_available), // Gambar default saat loading
            error = painterResource(id = R.drawable.no_image_available) // Gambar default saat gagal/error atau imageUrl kosong
        )

        // Semi-transparent Overlay
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color(0x99BF002E),
                        )
                    )
                )
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
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )

            // Title & Author
            Column {
                Text(
                    text = title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Row(horizontalArrangement = Arrangement.Start) {
                    AsyncImage(
                        model = profileUrl,
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape),
                        placeholder = painterResource(id = R.drawable.person_profil),
                        error = painterResource(id = R.drawable.person_profil),
                        contentScale = ContentScale.Crop
                    )
//                    Box(
//                        modifier = Modifier
//                            .clip(RoundedCornerShape(50.dp))
//                            .background(Color.Gray)
//                            .padding(1.dp)
//                    ) {
//                        val painter = painterResource(id = R.drawable.person_profil) // Gambar default profil
//                        Image(
//                            painter = painter,
//                            contentDescription = "Profile Image",
//                            modifier = Modifier
//                                .size(16.dp)
//                                .clip(RoundedCornerShape(50.dp)),
//                            contentScale = ContentScale.Crop
//                        )
//                    }
                    Text(
                        text = author,
                        modifier = Modifier.padding(start = 5.dp),
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}
fun requestCallPermission(activity: Context) {
    val REQUEST_CALL = 2

    if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(
            activity as Activity,
            arrayOf(Manifest.permission.CALL_PHONE),
            REQUEST_CALL
        )
    }
}