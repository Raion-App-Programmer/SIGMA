package com.example.login.lapor

import android.content.Context
import android.net.Uri
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.login.fitur_lapor.LaporanViewModel
import com.example.login.fitur_lapor.buttomNavbarLapor
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

fun uploadFileToFirebaseStorage(uri: Uri, context: Context, onSuccess: (String) -> Unit, onFailure: (Exception) -> Unit) {
    val storageRef = FirebaseStorage.getInstance().reference
    val fileRef = storageRef.child("uploads/${System.currentTimeMillis()}.jpg")

    fileRef.putFile(uri)
        .addOnSuccessListener {
            fileRef.downloadUrl.addOnSuccessListener { downloadUri ->
                onSuccess(downloadUri.toString()) // URL file yang telah diupload
            }
        }
        .addOnFailureListener { exception ->
            onFailure(exception)
        }
}

fun saveLaporanToFirestore(laporan: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("laporan")
        .add(laporan)
        .addOnSuccessListener {
            onSuccess()
        }
        .addOnFailureListener { exception ->
            onFailure(exception)
        }
}

@Composable
fun laporSigma3(navController: NavController, laporanViewModel: LaporanViewModel) {
    var errorMessage by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    fun uploadAndSendReport() {
        if (!isChecked) {
            Toast.makeText(context, "Validasi kebenarannya ya", Toast.LENGTH_SHORT).show()
            return
        }

        selectedImageUri?.let { uri ->
            uploadFileToFirebaseStorage(
                uri = uri,
                context = context,
                onSuccess = { downloadUrl ->
                    laporanViewModel.buktiUrl.value = downloadUrl
                    val laporan = laporanViewModel.toMap()

                    saveLaporanToFirestore(laporan,
                        onSuccess = {
                            navController.navigate(Routes.LaporBerhasil)
                        },
                        onFailure = { exception ->
                            errorMessage = "Gagal menyimpan laporan: ${exception.message}"
                        }
                    )
                },
                onFailure = { exception ->
                    errorMessage = "Gagal upload file: ${exception.message}"
                }
            )
        } ?: run {
            val laporan = laporanViewModel.toMap()
            saveLaporanToFirestore(laporan,
                onSuccess = {
                    navController.navigate(Routes.LaporBerhasil)
                },
                onFailure = { exception ->
                    errorMessage = "Gagal menyimpan laporan: ${exception.message}"
                }
            )
        }
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
                                navController.navigate(Routes.LaporSigma2)
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
                            .width(145.dp)
                    )

                    Text(
                        text = "3/3",
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
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Pernyataan",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                )
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .width(390.dp)
                        .height(110.dp)
                        .padding(top = 10.dp, start = 20.dp, end = 20.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Laporan yang saya buat akurat dan dapat dipertanggungjawabkan.",
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(start = 40.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 28.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = { isChecked = it },
                                colors = CheckboxDefaults.colors(
                                    checkmarkColor = Color.Red, //warna nya centang saat di klik
                                )
                            )
                            Spacer(
                                modifier = Modifier
                                    .width(8.dp)
                            )
                            Text(text = "Ya, Saya Setuju")
                        }
                    }
                }

                Spacer(
                    modifier = Modifier
                        .height(470.dp)
                )

                Button(
                    onClick = {uploadAndSendReport()},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .background(color = Color.Transparent)
                        .padding(start = 20.dp, end = 20.dp),
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
                        Text(
                            text = "Kirim Laporan",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }


            }

        }
        buttomNavbarLapor(navController)
    }
}