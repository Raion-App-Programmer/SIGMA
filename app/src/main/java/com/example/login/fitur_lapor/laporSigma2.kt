package com.example.login.lapor

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.login.R
import com.example.login.Routes
import com.example.login.fitur_lapor.buttomNavbarLapor


fun getFileName(context: Context, uri: Uri): String {
    var name = "IMG/VID Selected"
    context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        if (nameIndex != -1 && cursor.moveToFirst()) {
            name = cursor.getString(nameIndex)
        }
    }
    return name
}

@Composable
fun laporSigma2(navController : NavController) {
    var judul by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }

    val dark_grey = colorResource(id = R.color.dark_grey)
    val dark0_grey = colorResource(id = R.color.dark0_grey)

    var selectedFileName by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedFileName = uri?.let { getFileName(context, it) }
    }

    Box(
        modifier = Modifier
            .width(412.dp)
            .height(917.dp)
            .background(color = Color(0xFFF7EAEB))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
        ) {
            Box(
                modifier = Modifier
                    .width(412.dp)
                    .height(119.dp)
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFFC41532),
                                Color(0xFF431B3B)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier
                            .height(24.dp)
                            .clickable {
                                navController.navigate(Routes.LaporSigma1)
                            }
                    )

                    Spacer(
                        modifier = Modifier
                            .width(30.dp)
                    )

                    Text(
                        modifier = Modifier
                            .width(122.dp)
                            .height(25.dp),
                        text = "Lapor Sigma",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(
                        modifier = Modifier
                            .width(150.dp)
                    )

                    Text(
                        text = "2/3",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .height(30.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Text(
                    text = "Judul Laporan",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                TextField(
                    value = judul,
                    onValueChange = { judul = it },
                    placeholder = {
                        androidx.compose.material3.Text(
                            "Judul Laporan",
                            color = dark_grey
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(18.dp),
                        )
                        .clip(RoundedCornerShape(18.dp))
                        .heightIn(max = 50.dp),
                    maxLines = Int.MAX_VALUE,

                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    )
                )

                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )

                Text(
                    text = "Deskripsi Laporan",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                TextField(
                    value = deskripsi,
                    onValueChange = { deskripsi = it },
                    placeholder = {
                        androidx.compose.material3.Text(
                            "Deskripsi Laporan",
                            color = dark_grey
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .height(210.dp)
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(18.dp),
                        )
                        .clip(RoundedCornerShape(18.dp)),

                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    )
                )
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )

                Text(
                    text = "Foto/ Video",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Button(
                    onClick = { launcher.launch("*/*") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(top = 10.dp)
                        .background(color = Color.Transparent),
                    shape = RoundedCornerShape(16.dp),
                    contentPadding = PaddingValues()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = Color(0xFF616161),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .align(alignment = Alignment.Center),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.CloudUpload,
                                contentDescription = "Unggah",
                                tint = Color.White,
                                modifier = Modifier
                                    .height(24.dp)
                                    .width(24.dp)
                            )
                            Spacer(
                                modifier = Modifier
                                    .width(5.dp)
                            )
                            Text(
                                text = selectedFileName ?: "Unggah Media",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }


                Spacer(
                    modifier = Modifier
                        .height(154.dp)
                )

                Button(
                    onClick = { navController.navigate(Routes.LaporSigma3) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .background(color = Color.Transparent),
                    shape = RoundedCornerShape(16.dp),
                    contentPadding = PaddingValues()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(0xFFC41532),
                                        Color(0xFF431B3B)
                                    )
                                ),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.material3.Text(
                            text = "Selanjutnya",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }


            }

        }
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
                                    navController.navigate(Routes.LaporSigma1)
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


                }
            }
        }
        buttomNavbarLapor(navController)
    }
}
