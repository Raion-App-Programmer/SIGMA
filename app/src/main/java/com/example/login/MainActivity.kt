package com.example.login

import BeritaDetail
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.login.Routes.BeritaDetail
import com.example.login.Routes.PanduanKebakaran
import com.example.mytestsigma.ui.theme.Dashboard
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase BEFORE using auth
        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()

        // Firebase Storage
        val db = Firebase.firestore

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Routes.LandingPage1) {
                composable(Routes.LandingPage1) {
                    LandingPage1(navController)
                }
                composable(Routes.LandingPage2) {
                    LandingPage2(navController)
                }
                composable(Routes.Login) {
                    Login(navController, authViewModel = viewModel())
                }
                composable(Routes.SignUp) {
                    val authViewModel: AuthViewModel = viewModel()
                    SignUp(navController, authViewModel)
                }
                composable(Routes.Verification) {
                    Verification(navController, authViewModel = AuthViewModel())
                }
                composable(Routes.LoginBerhasil) {
                    LoginBerhasil(navController)
                }
                composable(Routes.OnBoarding) {
                    OnBoarding(navController)
                }
                composable(Routes.Dashboard) {
                    Dashboard(navController)
                }
                composable(Routes.SignUpBerhasil) {
                    SignUpBerhasil(navController)
                }
                composable(Routes.PanduanBanjir) {
                    PanduanBanjir(navController)
                }
                composable(Routes.PanduanKebakaran) {
                    PanduanKebakaran(navController)
                }
                composable(Routes.BeritaTerkini) {
                    BeritaTerkini(navController)
                }
                composable(
                    route = "BeritaDetail/{newsId}",
                    arguments = listOf(navArgument("newsId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val newsId = backStackEntry.arguments?.getString("newsId")

                    // Ensure we don't pass null to Firebase
                    if (!newsId.isNullOrBlank()) {
                        BeritaDetail(newsId, NewsViewModel())  // Only pass a valid newsId
                    } else {
                        // Show an error screen or navigate back
                        Text("Error: Invalid news ID")
                    }
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
