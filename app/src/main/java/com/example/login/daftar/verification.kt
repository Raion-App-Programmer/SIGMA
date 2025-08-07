package com.example.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun verification(navController: NavController, authViewModel: AuthViewModel) {
    LaunchedEffect(Unit) {
        delay(1200)
        navController.navigate(Routes.VerificationFilled)
    }

    val font_grey = Color(0xFF999999)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color(0xFFF5F5F5)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "Hampir Sampai!",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(id = R.drawable.send_otp_verification),
                contentDescription = "Verification illustration",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Masukkan angka 4-digit yang dikirim melalui email",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center

            ){
                Text(text = "loremipsum@gmail.com",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                )

                Spacer(modifier = Modifier
                    .width(5.dp))

                Text(text = "untuk verifikasi",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                )
            }

            Spacer(modifier = Modifier
                .height(40.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                // Card 1
                val isPressed1 = remember { mutableStateOf(false) }
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEAEAEA)),
                    modifier = Modifier
                        .width(76.dp)
                        .height(76.dp)
                        .clickable { isPressed1.value = !isPressed1.value }
                        .then(
                            if (isPressed1.value) {
                                Modifier.border(2.dp, Color(0xFFC41532), RoundedCornerShape(10.dp))
                            } else {
                                Modifier
                            }
                        ),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(100.dp)
                ) {}

                Spacer(modifier = Modifier.width(15.dp))

                // Card 2
                val isPressed2 = remember { mutableStateOf(false) }
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEAEAEA)),
                    modifier = Modifier
                        .width(76.dp)
                        .height(76.dp)
                        .clickable { isPressed2.value = !isPressed2.value }
                        .then(
                            if (isPressed2.value) {
                                Modifier.border(2.dp, Color(0xFFC41532), RoundedCornerShape(10.dp))
                            } else {
                                Modifier
                            }
                        ),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(100.dp)
                ) {}

                Spacer(modifier = Modifier.width(15.dp))

                // Card 3
                val isPressed3 = remember { mutableStateOf(false) }
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEAEAEA)),
                    modifier = Modifier
                        .width(76.dp)
                        .height(76.dp)
                        .clickable { isPressed3.value = !isPressed3.value }
                        .then(
                            if (isPressed3.value) {
                                Modifier.border(2.dp, Color(0xFFC41532), RoundedCornerShape(10.dp))
                            } else {
                                Modifier
                            }
                        ),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(100.dp)
                ) {}

                Spacer(modifier = Modifier.width(15.dp))

                // Card 4
                val isPressed4 = remember { mutableStateOf(false) }
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEAEAEA)),
                    modifier = Modifier
                        .width(76.dp)
                        .height(76.dp)
                        .clickable { isPressed4.value = !isPressed4.value }
                        .then(
                            if (isPressed4.value) {
                                Modifier.border(2.dp, Color(0xFFC41532), RoundedCornerShape(10.dp))
                            } else {
                                Modifier
                            }
                        ),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(100.dp)
                ) {}
            }

            Spacer(modifier = Modifier
                .height(40.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Tidak menerima pesan apa pun? ",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Kirim ulang",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFFC41532),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.
            height(5.dp))
        }
    }
}