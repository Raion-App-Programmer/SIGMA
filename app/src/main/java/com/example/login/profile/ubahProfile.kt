package com.example.login.profile

import alertUbahData
import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.login.R
import com.example.login.Routes
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
import java.io.File
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import com.example.login.lapor.getFileName
import kotlinx.coroutines.tasks.await

// Pastikan UbahProfilViewModel ada di package ini: com.example.login.profile
import com.example.login.profile.UbahProfilViewModel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

// --- Firestore and Storage Utility Functions ---
fun uploadFileToCloudinary(
    uri: Uri,
    context: Context,
    onSuccess: (String) -> Unit,
    onFailure: (Exception) -> Unit
) {
    val contentResolver = context.contentResolver
    val inputStream = contentResolver.openInputStream(uri)
    val fileName = getFileName(context, uri) ?: "upload"

    val bytes = inputStream?.readBytes()
    if (bytes == null) {
        onFailure(Exception("Gagal membaca file"))
        return
    }

    val requestBody = bytes.toRequestBody("image/*".toMediaTypeOrNull())

    val multipartBody = MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart("file", fileName, requestBody)
        .addFormDataPart("upload_preset", "sigmaRaion")
        .build()

    val request = Request.Builder()
        .url("https://api.cloudinary.com/v1_1/dydoectss/image/upload")
        .post(multipartBody)
        .build()

    val client = OkHttpClient()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Handler(Looper.getMainLooper()).post {
                onFailure(e)
            }
        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) {
                Handler(Looper.getMainLooper()).post {
                    onFailure(Exception("Upload gagal: ${response.message}"))
                }
            } else {
                val responseBody = response.body?.string()
                val json = JSONObject(responseBody ?: "{}")
                val url = json.optString("secure_url")
                if (url.isNotEmpty()) {
                    Handler(Looper.getMainLooper()).post {
                        onSuccess(url)
                    }
                } else {
                    Handler(Looper.getMainLooper()).post {
                        onFailure(Exception("URL upload tidak ditemukan"))
                    }
                }
            }
        }
    })
}

fun saveUbahProfileToFirestore(userId: String, ubahProfile: Map<String, Any?>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("data profile").document(userId)
        .set(ubahProfile, SetOptions.merge())
        .addOnSuccessListener {
            onSuccess()
        }
        .addOnFailureListener { exception ->
            onFailure(exception)
        }
}

// --- Composable ubahProfile ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ubahProfile(navController: NavController, ubahProfilViewModel: UbahProfilViewModel) {

    val context = LocalContext.current
    val currentUser = FirebaseAuth.getInstance().currentUser
    val userId = currentUser?.uid

    var reauthEmailInput by remember { ubahProfilViewModel.reauthEmail }
    var reauthPasswordInput by remember { ubahProfilViewModel.reauthPassword }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(userId) {
        if (userId == null) {
            Toast.makeText(context, "Pengguna belum login", Toast.LENGTH_SHORT).show()
            navController.navigate(Routes.Login) {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
            }
            return@LaunchedEffect
        }
        ubahProfilViewModel.loadProfileData(userId, context)

    }

    if (showSuccessDialog) {
        alertUbahData(
            navController,
            onDismiss = { showSuccessDialog = false },
            onConfirm = { showSuccessDialog = false }
        )
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            ubahProfilViewModel.buktiUrl.value = it.toString() // Preview local image
        }
    }

    suspend fun performProfileUpdate(isAfterReauth: Boolean = false) {
        val user = FirebaseAuth.getInstance().currentUser ?: run {
            Toast.makeText(context, "Sesi berakhir. Mohon login kembali.", Toast.LENGTH_SHORT).show()
            Log.e("AuthFlow", "ERROR: CurrentUser null saat mencoba update.")
            navController.navigate(Routes.Login)
            return
        }

        val newEmail = ubahProfilViewModel.email.value.trim()
        val newPassword = ubahProfilViewModel.kataSandi.value

        var needsReauth = false

        try {
            // --- Tahap 1: Coba Update Operasi Sensitif (Email & Password) ---

            // Cek jika email diubah
            if (newEmail.isNotBlank() && newEmail != user.email) {
                Log.d("AuthFlow", "Mencoba mengubah email dari ${user.email} ke $newEmail")
                try {
                    // PENTING: Firebase akan otomatis mengirim email verifikasi.
                    // Email lama tetap aktif sampai email baru diverifikasi.
                    user.updateEmail(newEmail).await()
                    Log.d("AuthFlow", "Perintah ubah email berhasil. Firebase akan mengirim verifikasi ke $newEmail.")
                    Toast.makeText(context, "Link verifikasi telah dikirim ke $newEmail. Cek inbox Anda untuk menyelesaikan.", Toast.LENGTH_LONG).show()
                } catch (e: FirebaseAuthRecentLoginRequiredException) {
                    needsReauth = true
                    Log.w("AuthFlow", "Perlu re-autentikasi untuk mengubah email.")
                }
            }

            // Cek jika password diubah
            if (newPassword.isNotBlank()) {
                Log.d("AuthFlow", "Mencoba mengubah kata sandi.")
                try {
                    user.updatePassword(newPassword).await()
                    Log.d("AuthFlow", "Kata sandi berhasil diperbarui di Firebase Auth.")
                    ubahProfilViewModel.kataSandi.value = "" // Kosongkan field setelah berhasil
                    Toast.makeText(context, "Kata sandi berhasil diubah.", Toast.LENGTH_SHORT).show()
                } catch (e: FirebaseAuthRecentLoginRequiredException) {
                    needsReauth = true
                    Log.w("AuthFlow", "Perlu re-autentikasi untuk mengubah kata sandi.")
                }
            }

            // --- Tahap 2: Tampilkan Dialog Re-autentikasi jika diperlukan ---
            if (needsReauth && !isAfterReauth) {
                Log.d("AuthFlow", "Menampilkan dialog re-autentikasi.")
                ubahProfilViewModel.reauthEmail.value = user.email ?: ""
                ubahProfilViewModel.reauthPassword.value = ""
                ubahProfilViewModel.showReauthDialog.value = true
                Toast.makeText(context, "Untuk keamanan, mohon konfirmasi ulang akun Anda.", Toast.LENGTH_LONG).show()
                return // Hentikan proses, tunggu input dari dialog
            }

            // --- Tahap 3: Update Data ke Firestore & Storage ---
            // Tahap ini hanya berjalan jika tidak ada kebutuhan re-auth, atau jika re-auth sudah berhasil.
            Log.d("AuthFlow", "Melanjutkan update data ke Firestore.")

            val onFirestoreSuccess = {
                Log.d("AuthFlow", "Update Firestore berhasil.")
                showSuccessDialog = true
            }
            val onFirestoreFailure = { e: Exception ->
                Log.e("AuthFlow", "Gagal menyimpan ke Firestore: ${e.message}", e)
                Toast.makeText(context, "Gagal menyimpan data profil: ${e.message}", Toast.LENGTH_SHORT).show()
            }

            // Jika ada gambar baru yang dipilih, upload dulu
            if (selectedImageUri != null) {
                uploadFileToCloudinary(
                    uri = selectedImageUri!!,
                    context = context,
                    onSuccess = { secureUrl ->
                        ubahProfilViewModel.buktiUrl.value = secureUrl
                        Log.d("BUKTI_URLS", "Isi buktiUrls: ${ubahProfilViewModel.buktiUrl.value}")
                        saveUbahProfileToFirestore(userId!!, ubahProfilViewModel.toMap(), onFirestoreSuccess, onFirestoreFailure)
                    },
                    onFailure = { e ->
                        Toast.makeText(context, "Gagal upload foto: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                )
            } else {
                // Jika tidak ada gambar baru, langsung simpan data lainnya
                saveUbahProfileToFirestore(userId!!, ubahProfilViewModel.toMap(), onFirestoreSuccess, onFirestoreFailure)
            }

        } catch (e: Exception) {
            // Menangkap error umum lainnya yang mungkin terjadi
            Log.e("AuthFlow", "Terjadi error tak terduga saat update: ${e.javaClass.name}", e)
            Toast.makeText(context, "Terjadi kesalahan: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF7EAEB))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(248.dp)
                    .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
                    .background(
                       Color(0xFFBF002E),
                    ),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp, vertical = 64.dp)
                        .align(Alignment.TopCenter),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp).clickable { navController.navigate(Routes.Profile) }
                    )
                    Text(
                        text = "Ubah Profil",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White

                    )
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Setting",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            // Form Card
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .offset(y = (-80).dp),
                shape = RoundedCornerShape(30.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 24.dp, end = 24.dp, top = 80.dp, bottom = 24.dp)
                        .verticalScroll(rememberScrollState()), // Make form scrollable
                    horizontalAlignment = Alignment.Start
                ) {
                    val dark_grey = colorResource(id = R.color.dark_grey)
                    val dark0_grey = colorResource(id = R.color.dark0_grey)

                    // Nama
                    Text(text = "Nama", fontSize = 14.sp, fontWeight = FontWeight(700), color = Color.Black)
                    Spacer(modifier = Modifier.height(3.dp))
                    OutlinedTextField(
                        value = ubahProfilViewModel.nama.value,
                        onValueChange = { ubahProfilViewModel.nama.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "Nama", color = dark_grey) },
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White, unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = dark0_grey, unfocusedIndicatorColor = dark0_grey
                        ),
                        textStyle = TextStyle(fontSize = 14.sp),
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    // Email
                    Text(text = "Email", fontSize = 14.sp, fontWeight = FontWeight(700), color = Color.Black)
                    Spacer(modifier = Modifier.height(3.dp))
                    OutlinedTextField(
                        value = ubahProfilViewModel.email.value,
                        onValueChange = { ubahProfilViewModel.email.value = it },
                        placeholder = { Text("Email", color = dark_grey) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White, unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = dark0_grey, unfocusedIndicatorColor = dark0_grey
                        ),
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    // Kata Sandi
                    Text(text = "Kata Sandi", fontSize = 14.sp, fontWeight = FontWeight(700), color = Color.Black)
                    Spacer(modifier = Modifier.height(3.dp))
                    OutlinedTextField(
                        value = ubahProfilViewModel.kataSandi.value,
                        onValueChange = { ubahProfilViewModel.kataSandi.value = it },
                        placeholder = { Text("Isi untuk mengubah kata sandi", color = dark_grey) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White, unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = dark0_grey, unfocusedIndicatorColor = dark0_grey
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    // Nomor Telepon
                    Text(text = "Nomor Telepon", fontSize = 14.sp, fontWeight = FontWeight(700), color = Color.Black)
                    Spacer(modifier = Modifier.height(5.dp))
                    OutlinedTextField(
                        value = ubahProfilViewModel.nomorTelepon.value,
                        onValueChange = { ubahProfilViewModel.nomorTelepon.value = it },
                        placeholder = { Text("Nomor Telepon", color = dark_grey) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White, unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = dark0_grey, unfocusedIndicatorColor = dark0_grey
                        ),
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    // Alamat
                    Text(text = "Alamat", fontSize = 14.sp, fontWeight = FontWeight(700), color = Color.Black)
                    Spacer(modifier = Modifier.height(3.dp))
                    OutlinedTextField(
                        value = ubahProfilViewModel.alamat.value,
                        onValueChange = { ubahProfilViewModel.alamat.value = it },
                        placeholder = { Text("Alamat", color = dark_grey) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White, unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = dark0_grey, unfocusedIndicatorColor = dark0_grey
                        ),
                        maxLines = 3
                    )
                }
            }
        }

        // Profile Image and Edit Button
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 125.dp) // Sesuaikan posisi vertikal
        ) {
            val imageUrl = selectedImageUri?.toString() ?: ubahProfilViewModel.buktiUrl.value
            val painter = rememberAsyncImagePainter(
                model = if (imageUrl.isNullOrEmpty()) R.drawable.person_profil else imageUrl,
                error = painterResource(id = R.drawable.person_profil) // Fallback image
            )

            Image(
                painter = painter,
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(119.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
                    .border(4.dp, Color.White, CircleShape)
                    .clickable { imagePickerLauncher.launch("image/*") },
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = 4.dp, y = 4.dp)
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable { imagePickerLauncher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.edit_profil_icon),
                    contentDescription = "Edit Icon",
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        // Tombol Simpan
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp)
        ) {
            Button(
                onClick = {
                    val emptyFields = listOf(
                        "Nama" to ubahProfilViewModel.nama.value,
                        "Email" to ubahProfilViewModel.email.value,
                        "Nomor Telepon" to ubahProfilViewModel.nomorTelepon.value,
                        "Alamat" to ubahProfilViewModel.alamat.value
                    ).filter { it.second.isBlank() }.map { it.first }

                    if (emptyFields.isNotEmpty()) {
                        val errorMessage = "Kolom ${emptyFields.joinToString(", ")} belum terisi."
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                    } else {
                        coroutineScope.launch {
                            performProfileUpdate()
                        }
                    }
                },
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = androidx.compose.foundation.layout.PaddingValues()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                           Color(0xFFBF002E),
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

    // --- Re-authentication Dialog ---
    if (ubahProfilViewModel.showReauthDialog.value) {
        AlertDialog(
            onDismissRequest = { ubahProfilViewModel.showReauthDialog.value = false },
            title = { Text("Konfirmasi Ulang Akun") },
            text = {
                Column {
                    Text("Untuk keamanan, masukkan kembali kata sandi Anda untuk melanjutkan.")
                    Spacer(Modifier.height(16.dp))
                    OutlinedTextField(
                        value = reauthPasswordInput,
                        onValueChange = { reauthPasswordInput = it },
                        label = { Text("Kata Sandi Saat Ini") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    coroutineScope.launch {
                        if (reauthPasswordInput.isBlank()) {
                            Toast.makeText(context, "Kata sandi tidak boleh kosong.", Toast.LENGTH_SHORT).show()
                            return@launch
                        }

                        val credential = EmailAuthProvider.getCredential(currentUser?.email!!, reauthPasswordInput)
                        try {
                            currentUser.reauthenticate(credential).await()
                            ubahProfilViewModel.showReauthDialog.value = false
                            reauthPasswordInput = "" // Kosongkan password
                            Log.d("AuthFlow", "Re-autentikasi berhasil! Mencoba update lagi.")
                            // Setelah berhasil, panggil lagi fungsi update dengan flag isAfterReauth = true
                            performProfileUpdate(isAfterReauth = true)
                        } catch (e: Exception) {
                            Log.e("AuthFlow", "Re-autentikasi gagal: ${e.message}", e)
                            val errorMessage = if (e is FirebaseAuthInvalidCredentialsException) {
                                "Kata sandi yang Anda masukkan salah."
                            } else {
                                "Re-autentikasi gagal. Coba lagi nanti."
                            }
                            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                            reauthPasswordInput = "" // Kosongkan password
                        }
                    }
                }) {
                    Text("Konfirmasi")
                }
            },
            dismissButton = {
                Button(onClick = {
                    ubahProfilViewModel.showReauthDialog.value = false
                    reauthPasswordInput = "" // Kosongkan password
                }) {
                    Text("Batal")
                }
            }
        )
    }
}