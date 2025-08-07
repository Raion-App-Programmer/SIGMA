package com.example.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
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
    val dark0_grey = colorResource(id = R.color.dark0_grey)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFFF5F5F5)
            )
    )
    {
        Image(
            painter = painterResource(id = R.drawable.circle_daftar),
            contentDescription = "Background decoration",
            modifier = Modifier
                .width(340.dp)
                .height(259.dp)
                .align(Alignment.TopEnd),
        )

            Spacer(modifier = Modifier
                .height(70.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(25.dp),
                ) {
                    Spacer(modifier = Modifier
                        .height(240.dp))
                    Text(
                        text = "Login",
                        fontSize = 52.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Text(
                        text = "Masuk ke Akun Anda",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )

                    Spacer(modifier = Modifier.height(50.dp))

                    // Illustration
                    Image(
                        painter = painterResource(id = R.drawable.ilustrasi_daftar),
                        contentDescription = "Login illustration",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .offset(x = -50.dp)
                    )

                    Spacer(modifier = Modifier.height(37.dp))

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
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 8.dp)

                            .padding(bottom = 7.dp)
                            .border(
                                width = 2.dp,
                                color = Color(0xFFEAEAEA),
                                shape = RoundedCornerShape(8.dp)
                            ),
                        shape = RoundedCornerShape(8.dp),
                        colors = outlinedTextFieldColors(
                            containerColor = Color(0xFFEAEAEA)
                        )
                    )

                    OutlinedTextField(
                        value = kataSandi,
                        onValueChange = { kataSandi = it },
                        placeholder = { Text("Kata Sandi ", color = dark_grey) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Email",
                                tint = dark_grey
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 1.dp)
                            .border(
                                width = 2.dp,
                                color = Color(0xFFEAEAEA),
                                shape = RoundedCornerShape(8.dp)
                            ),
                        colors = outlinedTextFieldColors(
                            containerColor = Color(0xFFEAEAEA)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        visualTransformation = if (kataSandiVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { kataSandiVisibility = !kataSandiVisibility }) {
                                Icon(
                                    imageVector = if (kataSandiVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                    contentDescription = "Toggle Password Visibility",
                                    tint = dark_grey
                                )
                            }
                        }
                    )

                    Spacer(modifier = Modifier
                        .height(20.dp))

                    Button(
                        onClick = {
                            authViewModel.login(email, kataSandi)
                             },
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 1.dp)
                            .fillMaxWidth()
                            .height(48.dp)
                            .background(color = Color.Transparent),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = Color(0xFFC41532),
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Masuk", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier
                        .height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.Center

                    ) {
                        Text(
                            text = "Belum memiliki akun? ",
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )

                        Text(
                            text = " Daftar",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFFC41532),
                            modifier = Modifier.clickable {
                                navController.navigate(Routes.SignUp)
                            }
                        )

                    }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 180.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 38.dp),
                    horizontalArrangement = Arrangement.Center

                ) {
                    Text(
                        text = "Dengan membuat akun, Anda menyetujui",
                        fontSize = 10.sp, color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = " Ketentuan",
                        textDecoration = TextDecoration.Underline,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp, color = Color.White
                    )
                    Text(
                        text = " kami dan telah",
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp, color = Color.White
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 38.dp),
                    horizontalArrangement = Arrangement.Center

                ) {
                    Text(
                        text = " membaca serta mengakui",
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp, color = Color.White
                    )
                    Text(
                        text = " Pernyataan Privasi Global.",
                        textDecoration = TextDecoration.Underline,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp, color = Color.White
                    )
                }

            }
        }
    }
}



@Preview
@Composable
fun loginPreview() {
    val navController = rememberNavController()
    login(navController = navController, authViewModel = viewModel())
}