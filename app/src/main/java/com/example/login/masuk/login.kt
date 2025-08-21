package com.example.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun login(navController: NavController, authViewModel: AuthViewModel) {

    var email by remember { mutableStateOf("") }
    var kataSandi by remember { mutableStateOf("") }
    var kataSandiVisibility by remember { mutableStateOf(false) }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.LoginSuccess -> {
                navController.navigate(Routes.LoginBerhasil)
            }
            is AuthState.Error -> {
                Toast.makeText(
                    context,
                    (authState.value as AuthState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> Unit
        }
    }

    val dark_grey = colorResource(id = R.color.dark_grey)

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        val maxWidth = this.maxWidth
        val maxHeight = this.maxHeight

        Image(
            painter = painterResource(id = R.drawable.circle_daftar),
            contentDescription = "Background decoration",
            modifier = Modifier
                .width(maxWidth * 0.8f)
                .height(maxHeight * 0.3f)
                .align(Alignment.TopEnd),
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = maxWidth * 0.07f),
        ) {
            Spacer(modifier = Modifier.height(maxHeight * 0.27f))

            Text(
                text = "Login",
                fontSize = (maxWidth.value / 8).sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "Masuk ke Akun Anda",
                fontSize = (maxWidth.value / 18.5).sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )

            Spacer(modifier = Modifier.height(maxHeight * 0.02f))

            Image(
                painter = painterResource(id = R.drawable.ilustrasi_daftar),
                contentDescription = "Login illustration",
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(maxHeight * 0.22f)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(maxHeight * 0.02f))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email", color = dark_grey) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.MailOutline,
                        contentDescription = "Email",
                        tint = dark_grey
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFEAEAEA),
                    focusedContainerColor = Color(0xFFEAEAEA),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color(0xFFC41532)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = kataSandi,
                onValueChange = { kataSandi = it },
                placeholder = { Text("Kata Sandi", color = dark_grey) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password",
                        tint = dark_grey
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                visualTransformation = if (kataSandiVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { kataSandiVisibility = !kataSandiVisibility }) {
                        Icon(
                            imageVector = if (kataSandiVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = "Toggle Password Visibility",
                            tint = dark_grey
                        )
                    }
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFEAEAEA),
                    focusedContainerColor = Color(0xFFEAEAEA),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color(0xFFC41532)
                )
            )

            Spacer(modifier = Modifier.height(maxHeight * 0.025f))

            Button(
                onClick = { authViewModel.login(email, kataSandi) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(maxHeight * 0.06f),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC41532))
            ) {
                Text(
                    text = "Masuk",
                    fontSize = (maxWidth.value / 28).sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(maxHeight * 0.025f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Belum memiliki akun? ",
                    fontWeight = FontWeight.Normal,
                    fontSize = (maxWidth.value / 32).sp,
                    color = Color.Gray
                )
                Text(
                    text = "Daftar",
                    fontSize = (maxWidth.value / 32).sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFFC41532),
                    modifier = Modifier.clickable {
                        navController.navigate(Routes.SignUp)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun loginPreview() {
    val navController = rememberNavController()
    login(navController = navController, authViewModel = viewModel())
}
