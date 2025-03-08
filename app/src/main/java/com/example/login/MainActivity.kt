package com.example.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login.Routes.verification
import com.example.mytestsigma.ui.theme.Dashboard
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase BEFORE using auth
        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Routes.landingPage1) {
                composable(Routes.landingPage1) {
                    landingPage1(navController)
                }
                composable(Routes.landingPage2) {
                    landingPage2(navController)
                }
                composable(Routes.login) {
                    login(navController, authViewModel = viewModel())
                }
                composable(Routes.signUp) {
                    val authViewModel: AuthViewModel = viewModel()
                    signUp(navController, authViewModel)
                }
                composable(Routes.verification) {
                    verification(navController)
                }
                composable(Routes.loginBerhasil) {
                    loginBerhasil(navController)
                }
                composable(Routes.onBoarding) {
                    onBoarding(navController)
                }
                composable(Routes.Dashboard) {
                    Dashboard(navController)
                }
                composable(Routes.signUpBerhasil) {
                    signUpBerhasil(navController)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // Handle logged-in user (e.g., navigate to a home screen)
        }
    }
}
