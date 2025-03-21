package com.example.login.fitur_profile


import alertUbahData
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.R
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.login.Routes
import com.example.login.lapor.saveLaporanToFirestore
import com.example.login.lapor.uploadFileToFirebaseStorage
import com.example.login.profile.UbahProfilViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage


fun uploadDataToFirebaseStorage(uri: Uri, context: Context, onSuccess: (String) -> Unit, onFailure: (Exception) -> Unit) {
    val storageRef = FirebaseStorage.getInstance().reference
    val fileRef = storageRef.child("profiles/${System.currentTimeMillis()}.jpg")

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


fun saveUbahProfileToFirestore(ubahProfile: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("data profile")
        .add(ubahProfile)
        .addOnSuccessListener {
            onSuccess()
        }
        .addOnFailureListener { exception ->
            onFailure(exception)
        }
}



@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ubahProfile(navController : NavController, ubahProfilViewModel: UbahProfilViewModel){

    val context = LocalContext.current
    val imageUri = rememberSaveable { mutableStateOf<Uri?>(null) } //state uri
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        alertUbahData(
            navController,
            onDismiss = { showDialog.value = false },
            onConfirm = { showDialog.value = false }
        )
    }
    // Launcher ->  memilih gambar dari galeri
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imageUri.value = it
            selectedImageUri = it
        } // Simpan uri
    }

    fun uploadAndSendReport() {
        // Jika ada gambar yang dipilih, upload dulu ke Firebase Storage
        if (selectedImageUri != null) {
            uploadDataToFirebaseStorage(
                uri = selectedImageUri!!,
                context = context,
                onSuccess = { downloadUrl ->
                    ubahProfilViewModel.buktiUrl.value = downloadUrl // Simpan URL gambar

                    // Setelah URL berhasil diperoleh, simpan ke Firestore
                    saveUbahProfileToFirestore(
                        ubahProfilViewModel.toMap(),
                        onSuccess = {
                            showDialog.value = true
                        },
                        onFailure = { exception ->
                            Log.e("FirestoreError", "Gagal menyimpan laporan: ${exception.message}")
                        }
                    )
                },
                onFailure = { exception ->
                    Log.e("FirestoreError", "Gagal upload file: ${exception.message}")
                }
            )
        } else {
            // Jika tidak ada gambar, langsung simpan laporan ke Firestore
            saveUbahProfileToFirestore(ubahProfilViewModel.toMap(),
                onSuccess = {
                    showDialog.value=true
                },
                onFailure = { exception ->
                    Log.e("FirestoreError", "Gagal menyimpan laporan: ${exception.message}")
                }
            )
        }
    }

    val dark_grey = colorResource(id = R.color.dark_grey)
    val dark0_grey = colorResource(id = R.color.dark0_grey)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
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
                    .fillMaxWidth()
                    .height(244.dp)
                    .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFFC41532),
                                Color(0xFF431B3B)
                            )
                        )
                    ),

                ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top,
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 38.dp, end = 38.dp, top = 64.dp),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier
                                .height(24.dp)
                                .clickable{(navController.navigate(Routes.Profile))}
                        )

                        Spacer(
                            modifier = Modifier
                                .width(91.dp)
                        )

                        Text(
                            modifier = Modifier
                                .width(106.dp)
                                .height(25.dp),
                            text = "Ubah Profil",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Spacer(
                            modifier = Modifier
                                .width(91.dp)
                        )

                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "setting",
                            tint = Color.White,
                            modifier = Modifier
                                .height(24.dp)
                                .width(24.dp)
                                .clickable { }
                        )

                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .width(371.dp)
                        .height(530.dp)
                        .padding(start = 21.dp, end = 20.dp)
                        .offset(y = -69.dp),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 51.dp, end = 52.dp, top = 81.dp),
                        horizontalAlignment = Alignment.Start
                    ){
                        Text(
                         text = "Nama",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(700),
                            color = Color.Black,
                        )

                        Spacer(
                            modifier = Modifier
                                .height(3.dp)
                        )

                        OutlinedTextField(
                            value = ubahProfilViewModel.nama.value,
                            onValueChange = { ubahProfilViewModel.nama.value = it  },
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 52.dp),
                            maxLines = Int.MAX_VALUE,
                            placeholder = { Text(text="Nama", color = dark_grey)
                                          },

                            shape = RoundedCornerShape(10.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = dark0_grey, // Warna border saat fokus
                                unfocusedBorderColor = dark0_grey,
                            ),
                            textStyle = TextStyle(fontSize = 14.sp)
                        )

                        Spacer(
                            modifier = Modifier
                                .height(10.dp)
                        )

                        Text(
                            text = "Email",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(700),
                            color = Color.Black,
                        )

                        Spacer(
                            modifier = Modifier
                                .height(3.dp)
                        )

                        OutlinedTextField(
                            value = ubahProfilViewModel.email.value,
                            onValueChange = { ubahProfilViewModel.email.value = it  },
                            placeholder = { Text("Email", color = dark_grey)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 52.dp),
                            maxLines = Int.MAX_VALUE,
                            shape = RoundedCornerShape(10.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = dark0_grey, // Warna border saat fokus
                                unfocusedBorderColor = dark0_grey,
                            )
                        )

                        Spacer(
                            modifier = Modifier
                                .height(10.dp)
                        )

                        Text(
                            text = "Kata Sandi",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(700),
                            color = Color.Black,
                        )

                        Spacer(
                            modifier = Modifier
                                .height(3.dp)
                        )

                        OutlinedTextField(
                            value = ubahProfilViewModel.kataSandi.value,
                            onValueChange = { ubahProfilViewModel.kataSandi.value = it  },
                            placeholder = { Text("Kata Sandi", color = dark_grey)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 52.dp),
                            maxLines = Int.MAX_VALUE,
                            shape = RoundedCornerShape(10.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = dark0_grey, // Warna border saat fokus
                                unfocusedBorderColor = dark0_grey,
                            )
                        )

                        Spacer(
                            modifier = Modifier
                                .height(10.dp)
                        )

                        Text(
                            text = "Nomor Telepon",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(700),
                            color = Color.Black,
                        )

                        Spacer(
                            modifier = Modifier
                                .height(5.dp)
                        )

                        OutlinedTextField(
                            value = ubahProfilViewModel.nomorTelepon.value,
                            onValueChange = { ubahProfilViewModel.nomorTelepon.value = it  },
                            placeholder = { Text("Nomor Telepon", color = dark_grey)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 52.dp),
                            maxLines = Int.MAX_VALUE,
                            shape = RoundedCornerShape(10.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = dark0_grey, // Warna border saat fokus
                                unfocusedBorderColor = dark0_grey,
                            )
                        )

                        Spacer(
                            modifier = Modifier
                                .height(10.dp)
                        )

                        Text(
                            text = "Alamat",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(700),
                            color = Color.Black,
                        )

                        Spacer(
                            modifier = Modifier
                                .height(3.dp)
                        )

                        OutlinedTextField(
                            value = ubahProfilViewModel.alamat.value,
                            onValueChange = { ubahProfilViewModel.alamat.value = it  },
                            placeholder = { Text("Alamat", color = dark_grey)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 52.dp),
                            maxLines = Int.MAX_VALUE,
                            shape = RoundedCornerShape(10.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = dark0_grey, // Warna border saat fokus
                                unfocusedBorderColor = dark0_grey,
                            )
                        )

                    }
                }

            }

        }
        Box(
            modifier = Modifier
                .padding(start = 147.dp, top = 119.dp)
        ) {
            val context = LocalContext.current
            val imageUrl = ubahProfilViewModel.buktiUrl.value

            // Activity Result Launcher untuk memilih gambar
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { selectedImageUri: Uri? ->
                selectedImageUri?.let {
                    ubahProfilViewModel.buktiUrl.value = it.toString() // Simpan URI gambar
                }
            }

            // Gunakan Coil untuk memuat gambar dari URI
            val painter = if (imageUrl.isNullOrEmpty()) {
                painterResource(id = R.drawable.person_profil) // Gambar default jika belum ada
            } else {
                rememberAsyncImagePainter(imageUrl)
            }

            Box(
                modifier = Modifier
                    .size(119.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
                    .border(4.dp, Color.White, CircleShape)
                    .clickable { launcher.launch("image/*") }
            ) {
                Image(
                    painter = painter,
                    contentDescription = "Profile Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            // Foto Profil
            Box(
                modifier = Modifier
                    .size(119.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
                    .border(4.dp, Color.White, CircleShape)
                    .clickable { launcher.launch("image/*") },
            ) {
                Image(
                    painter = painter,
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            }
        }
        Box(
            modifier = Modifier
                .padding(start = 230.dp, top = 118.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable { launcher.launch("image/*") }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.edit_profil_icon),
                    contentDescription = "icon edit",
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 115.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            Button(
                onClick = {if (ubahProfilViewModel.nama.value.isBlank() || ubahProfilViewModel.email.value.isBlank()
                    || ubahProfilViewModel.buktiUrl.value.isBlank() || ubahProfilViewModel.kataSandi.value.isBlank()
                    || ubahProfilViewModel.nomorTelepon.value.isBlank() || ubahProfilViewModel.alamat.value.isBlank()) {
                    Toast.makeText(context, "Isi dengan benar ya", Toast.LENGTH_SHORT).show()
                } else {
                    uploadAndSendReport()
                }},
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF431B3B),
                                    Color(0xFFC41532)
                                )
                            ),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Simpan",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}


