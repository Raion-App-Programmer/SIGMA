package com.example.login.fitur_panduan


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.login.R
import com.example.login.Routes
import com.example.login.Routes.Profile
import com.example.mytestsigma.ui.theme.getUserLocation

@Composable
fun buttomNavbarPanduan(navController: NavController, context: Context){
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

    Box(modifier = Modifier.fillMaxSize()){
        // Bottom dashboard - Now correctly placed inside the root Box
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
                        painter = painterResource(id = R.drawable.book_gray),
                        contentDescription = "Edit button",
                        modifier = Modifier
                            .width(32.dp)
                            .height(32.dp)
                            .offset(y = 24.dp, x = 32.dp)
                            .clickable {
                                navController.navigate("BeritaTerkini") {
                                }
                            }
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
                                navController.navigate(Profile)
                            }
                    )

                }


            }
        }
    }
}