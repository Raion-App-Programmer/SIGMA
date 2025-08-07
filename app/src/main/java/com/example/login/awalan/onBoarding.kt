package com.example.login.awalan

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavController
import com.example.login.R
import com.example.login.Routes

@Composable
fun onBoarding(navController: NavController) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_sigma_merah),
            contentDescription = "Logo Sigma lengkap",
            modifier = Modifier
                .width(screenWidth * 0.73f)
                .aspectRatio(295.5f / 127.5f)
                .align(Alignment.TopCenter)
                .offset(y = screenHeight * 0.16f)
        )

        Image(
            painter = painterResource(id = R.drawable.ilustrasi_membantu),
            contentDescription = "Ilustrasi membantu",
            modifier = Modifier
                .width(screenWidth * 1.03f)
                .aspectRatio(1f)
                .align(Alignment.TopCenter)
                .offset(y = screenHeight * 0.33f)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = "Aplikasi darurat yang menyediakan akses cepat ke layanan darurat, panduan pertolongan pertama, dan notifikasi bencana untuk warga Malang.",
                textAlign = TextAlign.Center,
                fontSize = (screenWidth.value * 0.035f).sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .padding(horizontal = screenWidth * 0.106f)
                    .padding(bottom = screenHeight * 0.05f)
            )

            // Register Button
            Button(
                onClick = { navController.navigate(Routes.SignUp) },
                modifier = Modifier
                    .height(screenHeight * 0.058f)
                    .width(screenWidth * 0.92f)
                    .background(color = Color.Transparent),
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = Color(0xFFBF002E),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Daftar",
                        fontSize = (screenWidth.value * 0.035f).sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.012f))

            // Login Button
            Button(
                onClick = { navController.navigate(Routes.Login) },
                modifier = Modifier
                    .height(screenHeight * 0.058f)
                    .width(screenWidth * 0.92f)
                    .background(color = Color.Transparent),
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(2.dp, Color(0xFFC7C7C7), RoundedCornerShape(16.dp))
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Masuk",
                        fontSize = (screenWidth.value * 0.035f).sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.06f))
        }
    }
}