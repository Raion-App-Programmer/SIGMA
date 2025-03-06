package com.example.login

import android.text.Layout
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController


@Composable
fun Verification(navController: NavController, authViewModel: AuthViewModel) {

    val font_grey= colorResource(id = R.color.font_grey)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFC41532),
                        Color(0xFF431B3B)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "Hampir Sampai!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp)
                )
            Spacer(modifier = Modifier.height(15.dp))

                Text(text = "Masukkan angka 4-digit yang dikirim melalui email",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp)
                )

            Row (
                modifier = Modifier
                    .fillMaxWidth()

            ){
                Text(text = "loremipsum@gmail.com",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(start = 30.dp)
                )
                    Spacer(modifier = Modifier.width(5.dp))

                Text(text = "untuk verifikasi",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                    textAlign = TextAlign.Start,

                )
            }
            Spacer(modifier = Modifier.height(40.dp))

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp)
            ){
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .width(76.dp)
                        .height(76.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(100.dp)
                ){

                }
                Spacer(modifier = Modifier.width(15.dp))
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .width(76.dp)
                        .height(76.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(100.dp)
                ){

                }
                Spacer(modifier = Modifier.width(15.dp))
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .width(76.dp)
                        .height(76.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(100.dp)
                ){

                }
                Spacer(modifier = Modifier.width(15.dp))
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .width(76.dp)
                        .height(76.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(100.dp)
                ){

                }
            }
            Spacer(modifier = Modifier.height(40.dp))
                Text(text = "Tidak menerima pesan apa pun? Kirim ulang",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            Spacer(modifier = Modifier.height(5.dp))

            Text(text = "Kirim ulang kode 00:30",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = font_grey,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )

        }
    }
    }

@Preview(showBackground = true)
@Composable
fun VerificationPreview() {
    val navController = rememberNavController()
    val fakeAuthViewModel = AuthViewModel() // Create a fake ViewModel if needed
    Verification(navController = navController, authViewModel = fakeAuthViewModel)
}
