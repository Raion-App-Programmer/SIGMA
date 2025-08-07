package com.example.login

import android.content.Context // Import ini
import android.util.Log // Import ini
import android.widget.Toast // Import ini
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext // Import ini
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth // Import ini
import kotlinx.coroutines.delay

@Composable
fun signUpBerhasil(navController: NavController) {
    val context = LocalContext.current // Dapatkan Context untuk Toast

    LaunchedEffect(Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            // Kirim email verifikasi
            currentUser.sendEmailVerification()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("SignUpSuccess", "Email verifikasi berhasil dikirim ke ${currentUser.email}.")
                        Toast.makeText(context, "Pendaftaran berhasil! Email verifikasi telah dikirim ke ${currentUser.email}. Mohon cek kotak masuk Anda.", Toast.LENGTH_LONG).show()
                    } else {
                        Log.e("SignUpSuccess", "Gagal mengirim email verifikasi: ${task.exception?.message}")
                        Toast.makeText(context, "Pendaftaran berhasil, tetapi gagal mengirim email verifikasi: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
        } else {
            // Ini bisa terjadi jika user tidak berhasil terdaftar atau logout terlalu cepat
            Log.e("SignUpSuccess", "CurrentUser is null setelah pendaftaran berhasil.")
            Toast.makeText(context, "Terjadi kesalahan saat pendaftaran. Silakan coba lagi.", Toast.LENGTH_LONG).show()
        }

        delay(1500) // Tunggu 1.5 detik
        navController.navigate(Routes.Login) // Lalu navigasi ke halaman Login
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFFBF002E),
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                    .width(348.dp)
                    .height(454.dp),
                shape = RoundedCornerShape(30.dp),
                elevation = CardDefaults.cardElevation(100.dp)
            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(25.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Image(
                        painter = painterResource(id = R.drawable.berhasil_fix),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .width(360.dp)
                            .padding(top = 35.dp)
                    )
                    Text(text = "Pendaftaran Berhasil!", // Mengubah teks
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 35.dp)
                    )
                    Text(text = "Email verifikasi telah dikirim. Tunggu sebentar...", // Mengubah teks
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 18.dp)
                            .padding(bottom = 18.dp)
                    )
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}