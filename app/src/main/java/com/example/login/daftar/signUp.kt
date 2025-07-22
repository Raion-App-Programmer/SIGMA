package com.example.login

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.Indication
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.material3.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(navController: NavController, authViewModel: AuthViewModel) {

    val nameError by authViewModel.nameError.observeAsState()
    val emailError by authViewModel.emailError.observeAsState()
    val passwordError by authViewModel.passwordError.observeAsState()
    val confirmPasswordError by authViewModel.confirmPasswordError.observeAsState()

    var nama by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var kataSandi by remember { mutableStateOf("") }
    var kataSandiVisibility by remember { mutableStateOf(false) }
    var konfirmKataSandi by remember { mutableStateOf("") }
    var konfirmKataSandiVisibility by remember { mutableStateOf(false) }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when(authState.value) {
            is AuthState.SignUpSuccess -> navController.navigate(Routes.SignUpBerhasil)
            is AuthState.Error -> Toast.makeText(context,
                (authState.value as AuthState.Error).message,Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Image(
            painter = painterResource(id = R.drawable.circle_daftar),
            contentDescription = "Background decoration",
            modifier = Modifier
                .width(340.dp)
                .height(259.dp)
                .align(Alignment.TopEnd),
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 33.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Spacer(modifier = Modifier.height(200.dp))

            // Title
            Text(
                text = "Daftar",
                fontSize = 52.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "Buat akun baru",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )

            Spacer(modifier = Modifier.height(37.dp))
            // Illustration
            Image(
                painter = painterResource(id = R.drawable.ilustrasi_daftar),
                contentDescription = "Sign up illustration",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .offset(x = -50.dp)
            )

            Spacer(modifier = Modifier.height(37.dp))

            // Form Fields
            OutlinedTextField(
                value = nama,
                onValueChange = { nama = it },
                placeholder = { Text("Nama", color = Color.Gray) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.PersonOutline,
                        contentDescription = "Nama",
                        tint = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFEAEAEA),
                    focusedContainerColor = Color(0xFFEAEAEA),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color(0xFFC41532)
                ),
            )
            if (nameError != null) {
                Text(text = nameError ?: "", color = Color.Red, fontSize = 12.sp)
            }

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email", color = Color.Gray) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.MailOutline,
                        contentDescription = "Email",
                        tint = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFEAEAEA),
                    unfocusedContainerColor = Color(0xFFEAEAEA),
                    focusedIndicatorColor = Color(0xFFC41532),
                    unfocusedIndicatorColor = Color.Transparent
                ),
            )
            if (emailError != null) {
                Text(text = emailError ?: "", color = Color.Red, fontSize = 12.sp)
            }

            OutlinedTextField(
                value = kataSandi,
                onValueChange = { kataSandi = it },
                placeholder = { Text("Kata Sandi", color = Color.Gray) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password",
                        tint = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFEAEAEA),
                    unfocusedContainerColor = Color(0xFFEAEAEA),
                    focusedIndicatorColor = Color(0xFFC41532),
                    unfocusedIndicatorColor = Color.Transparent
                ),
                visualTransformation = if (kataSandiVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { kataSandiVisibility = !kataSandiVisibility }) {
                        Icon(
                            imageVector = if (kataSandiVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = "Toggle Password Visibility",
                            tint = Color.Gray
                        )
                    }
                }
            )
            if (passwordError != null) {
                Text(text = passwordError ?: "", color = Color.Red, fontSize = 12.sp)
            }

            OutlinedTextField(
                value = konfirmKataSandi,
                onValueChange = { konfirmKataSandi = it },
                placeholder = { Text("Konfirmasi Kata Sandi", color = Color.Gray) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Confirm Password",
                        tint = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFEAEAEA),
                    unfocusedContainerColor = Color(0xFFEAEAEA),
                    focusedIndicatorColor = Color(0xFFC41532),
                    unfocusedIndicatorColor = Color.Transparent
                ),
                visualTransformation = if (konfirmKataSandiVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { konfirmKataSandiVisibility = !konfirmKataSandiVisibility }) {
                        Icon(
                            imageVector = if (konfirmKataSandiVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = "Toggle Password Visibility",
                            tint = Color.Gray
                        )
                    }
                }
            )
            if (confirmPasswordError != null) {
                Text(text = confirmPasswordError ?: "", color = Color.Red, fontSize = 12.sp)
            }

            // Sign Up Button
            Button(
                onClick = {
                    if (kataSandi != konfirmKataSandi) {
                        Toast.makeText(context, "Kedua kata sandi harus cocok", Toast.LENGTH_SHORT).show()
                    } else if (!isValidPassword(kataSandi)) {
                        Toast.makeText(
                            context,
                            "Kata sandi harus minimal 8 karakter, mengandung setidaknya 1 huruf besar, 1 huruf kecil, dan 1 angka.",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        val valid = authViewModel.validateSignUpInputs(nama, email, kataSandi, konfirmKataSandi)
                        if (valid){
                            authViewModel.signUp(
                                email = email,
                                password = kataSandi,
                                displayName = nama,
                                onSuccess = { userId ->
                                    writeUserToFirestore(userId, email, nama) {
                                        navController.navigate(Routes.SignUpBerhasil)
                                    }
                                },
                                onFailure =  { errorMessage ->
                                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                }
                            )
                            navController.navigate(Routes.Verification)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFC41532)
                )
            ) {
                Text(
                    text = "Daftar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(19.dp))

            // Login Link
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Sudah memiliki akun? ",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Text(
                    text = "Masuk",
                    fontSize = 14.sp,
                    color = Color(0xFFC41532),
                    modifier = Modifier
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            navController.navigate(Routes.Login)
                        }
                )
            }
        }
    }
}

fun writeUserToFirestore(
    userId: String,
    email: String,
    displayName: String,
    onComplete: () -> Unit
) {
    val db = FirebaseFirestore.getInstance()
    val userMap = hashMapOf(
        "email" to email,
        "displayName" to displayName
    )

    db.collection("users")
        .document(userId)
        .set(userMap)
        .addOnCompleteListener {
            onComplete()
        }
}

fun isValidPassword(password: String): Boolean {
    val passwordPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$".toRegex()
    return passwordPattern.matches(password)
}