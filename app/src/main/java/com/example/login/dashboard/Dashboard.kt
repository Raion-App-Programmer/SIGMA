package com.example.mytestsigma.ui.theme



import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.login.NewsViewModel
import com.example.login.R
import com.example.login.Routes
import com.example.login.Routes.Profile
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.util.Locale


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Dashboard(navController: NavController , viewModel: NewsViewModel = viewModel()) {
    val newsList by viewModel.newsList.collectAsState()
    val context = LocalContext.current
    val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION

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
    var userLocation by remember { mutableStateOf("Loading...") }
    var temperature by remember { mutableStateOf("Loading...") }
    var weatherCondition by remember { mutableStateOf("Loading...") }
    var userName by remember { mutableStateOf("Loading...") }
    var locationData by remember { mutableStateOf(LocationData("Loading...", "Loading...", "Loading...")) }


    // fetch location and weather data
    getUserLocationAndWeather(context) { data ->
       locationData = data
    }

    userName = FirebaseAuth.getInstance().currentUser?.displayName ?: "Pengguna"

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
                        text = "Halo, $userName!",
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
                            locationData.weatherCondition,
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .offset(y = 40.dp, x = 52.dp)
                        )
                        Text(
                            locationData.temperature, fontSize = 12.sp, color = Color.White,
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
                            locationData.address,
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
                        modifier = Modifier.offset(y = (-10).dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
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
                            modifier = Modifier.offset(y = 6.dp)
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .offset(y = (-10).dp)

                    ) {
                        Image(
                            painter = painterResource(R.drawable.kebakaran_darurat),
                            contentDescription = "Kebakaran darurat button", modifier = Modifier
                                .clickable { navController.navigate("PanduanKebakaran") }
                                .width(70.dp)
                                .height(80.dp)
                        )
                        Text(
                            "Kebakaran",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(top = 6.dp)
                                .clickable { navController.navigate("PanduanKebakaran")}
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
                                .height(80.dp)
                                .clickable{
                                    navController.navigate("panduanGempa")
                                })

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
                                .clickable { navController.navigate("p3")
                                }
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
            modifier = Modifier
                .fillMaxWidth() // Only fill width, not entire screen
                .padding(start = 16.dp, top = 500.dp), // Adjust top padding as needed
            verticalArrangement = Arrangement.Top, // Align items to the top
            horizontalAlignment = Alignment.Start // Align items to the start
        ) {
            Text(
                "Berita Terkini",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 20.dp) // Add padding for left alignment
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, top = 5.dp) // Add top padding for spacing
            ) {
                items(newsList) { newsItem ->
                    NewsCard(
                        imageUrl = newsItem.buktiUrl,
                        date = newsItem.tanggal,
                        title = newsItem.judul,
                        author = newsItem.nama,
                        onClick = {
                            navController.navigate("BeritaDetail/${newsItem.id}")
                        },
                        modifier = Modifier
                            .width(160.dp)
                            .height(240.dp)
                            .wrapContentSize(Alignment.Center)

                    )
                    Log.d("newsitem.imageurl", newsItem.buktiUrl)
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
                                navController.navigate("laporSigma1")
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

fun getUserLocation(activity: Context, navController: NavController) {
    val REQUEST_LOCATION = 1
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)

    if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                val lat = it.latitude
                val lon = it.longitude

                getCityFromCoordinates(activity, lat, lon) { cityPair ->
                    val city = cityPair.first
                    val isUrban = cityPair.second
                    val route = "emergency_services_screen/$lat/$lon/$city/$isUrban"
                    navController.navigate(route)
                }
            } ?: run {
                Toast.makeText(activity, "Gagal mendapatkan lokasi", Toast.LENGTH_SHORT).show()
            }
        }
    } else {
        // Request Location Permission
        ActivityCompat.requestPermissions(
            activity as Activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_LOCATION
        )
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

// gets city from longitude and latitude
fun getCityFromCoordinates(activity: Context, lat: Double, lon: Double, callback: (Pair<String, Boolean>) -> Unit) {
    val apiKey = "AIzaSyAA0QM7T1eE8n1APSAUcUd9C68esEBcP0o" // Replace with a valid API key
    val url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=$lat,$lon&key=$apiKey"

    val request = Request.Builder().url(url).build()
    val client = OkHttpClient()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.e("Location", "Error fetching location: ${e.message}")
            (activity as Activity).runOnUiThread {
                Toast.makeText(activity, "Gagal mendapatkan kota", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onResponse(call: Call, response: Response) {
            val json = response.body?.string() ?: return
            val cityPair = parseCityFromJson(json)

            (activity as Activity).runOnUiThread {
                callback(cityPair)
            }
        }
    })
}

// Parses the city name from JSON response
fun parseCityFromJson(json: String): Pair<String, Boolean> {
    val jsonObject = JSONObject(json)
    val results = jsonObject.getJSONArray("results")

    for (i in 0 until results.length()) {
        val addressComponents = results.getJSONObject(i).getJSONArray("address_components")
        for (j in 0 until addressComponents.length()) {
            val types = addressComponents.getJSONObject(j).getJSONArray("types")
            if (types.toString().contains("administrative_area_level_2")) {
                val city = addressComponents.getJSONObject(j).getString("long_name")
                val isUrban = city == "Jakarta" || city == "Surabaya" // Check for urban cities
                return Pair(city, isUrban)
            }
        }
    }
    return Pair("Lokasi Tidak Diketahui", false) // Default to rural
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
            .width(170.dp)
            .height(260.dp)
            .clip(RoundedCornerShape(20.dp))
            .padding(start = 5.dp, bottom = 16.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "News Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
        )

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color(0X99C41532),
                            Color(0X99431B3B),
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = date,
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier
                    .background(Color(0x88FFFFFF), RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )

            Column(verticalArrangement = Arrangement.Bottom) {
                Text(text = title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = author, color = Color.White, fontSize = 12.sp)
            }

            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.3f)),
                shape = RoundedCornerShape(50),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Selengkapnya", color = Color.White, fontSize = 12.sp)
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
                    contentDescription = "Page $page",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
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

// Data class to hold location and weather information
data class LocationData(
    val address: String,
    val temperature: String,
    val weatherCondition: String
)

fun getUserLocationAndWeather(context: Context, callback: (LocationData) -> Unit) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                val geocoder = Geocoder(context, Locale.getDefault())
                try {
                    val addresses = geocoder.getFromLocation(it.latitude, it.longitude, 1)

                    if (addresses != null && addresses.isNotEmpty()) {
                        val address = addresses[0]
                        val addressComponents = mutableListOf<String>()

                        address.thoroughfare?.let { addressComponents.add(it) } // street
                        address.locality?.let { addressComponents.add(it) } // city
                        address.adminArea?.let { addressComponents.add(it) } // state
                        address.countryName?.let { addressComponents.add(it) } // country

                        val formattedAddress = addressComponents.joinToString(", ")

                        // masih butuh weather API
                        val temperature = "29° C"
                        val weatherCondition = "Cerah"

                        callback(LocationData(formattedAddress, temperature, weatherCondition))
                    } else {
                        callback(LocationData("Alamat Tidak Ditemukan", "N/A", "N/A"))
                    }
                } catch (e: Exception) {
                    callback(LocationData("Gagal Mendapatkan Alamat", "N/A", "N/A"))
                }
            } ?: run {
                callback(LocationData("Lokasi Tidak Tersedia", "N/A", "N/A"))
            }
        }
    } else {
        callback(LocationData("Izin Lokasi Tidak Diberikan", "N/A", "N/A"))
    }
}

fun getWeatherData(latitude: Double, longitude: Double, callback: (String, String) -> Unit) {
    val apiKey = "8ab101584e182543f5ee7f958c65f3e6"
    val url = "https://api.openweathermap.org/data/2.5/weather?lat=$latitude&lon=$longitude&appid=$apiKey&units=metric&lang=id"

    val client = OkHttpClient()
    val request = Request.Builder().url(url).build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.e("Weather", "Error fetching weather: ${e.message}")
            callback("N/A", "N/A") // Handle error
        }

        override fun onResponse(call: Call, response: Response) {
            val json = response.body?.string()
            if (json != null) {
                try{
                    val jsonObject = JSONObject(json)
                    val main = jsonObject.getJSONObject("main")
                    val temperature = "${main.getDouble("temp")}° C"
                    val weather = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description")
                    callback(temperature, weather)
                } catch (e:Exception){
                    Log.e("Weather","Error parsing Json: ${e.message}")
                    callback("N/A","N/A")
                }

            } else {
                callback("N/A", "N/A")
            }
        }
    })
}

fun categorizeWeather(temperature: String, weatherDescription: String): String {
    val tempValue = temperature.replace("° C", "").toDoubleOrNull() ?: 0.0

    return when {
        tempValue > 30 && weatherDescription.contains("cerah", ignoreCase = true) -> "Cerah Panas"
        tempValue > 25 && weatherDescription.contains("awan", ignoreCase = true) -> "Berawan Hangat"
        weatherDescription.contains("hujan", ignoreCase = true) -> "Hujan"
        weatherDescription.contains("awan", ignoreCase = true) -> "Berawan"
        weatherDescription.contains("cerah", ignoreCase = true) -> "Cerah"
        else -> "Cuaca Tidak Diketahui"
    }
}

@Preview
@Composable
fun DashboardPreview() {
    val navController = rememberNavController()
    Dashboard(navController)
}

