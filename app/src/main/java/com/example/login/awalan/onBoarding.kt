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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.login.R
import com.example.login.Routes


@Composable
fun onBoarding(navController: NavController) {


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_sigma_merah),
            contentDescription = "Logo Sigma lengkap",
            modifier = Modifier.width(295.5.dp).height(127.5.dp).align(Alignment.TopCenter).offset(y = 132.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ilustrasi_membantu),
            contentDescription = "Logo Sigma lengkap",
            modifier = Modifier.width(419.dp).height(419.dp).align(Alignment.TopCenter).offset(y = 273.dp)
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(340.dp))
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Aplikasi darurat yang menyediakan akses cepat ke layanan darurat, panduan pertolongan pertama, dan notifikasi bencana untuk warga Malang.",
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(horizontal = 43.dp).offset(y = 140.dp)
            )
            Spacer(modifier = Modifier.height(25.dp))
            Spacer(modifier = Modifier.height(170.dp))
            Button(
                onClick = { navController.navigate(Routes.SignUp)},
                modifier = Modifier
                    .height(48.dp)
                    .width(372.dp)
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
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {navController.navigate(Routes.Login)},
                modifier = Modifier

                    .height(48.dp)
                    .width(372.dp)
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
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

