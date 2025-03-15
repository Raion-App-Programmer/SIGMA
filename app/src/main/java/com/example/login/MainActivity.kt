package com.example.login

import BeritaDetail
import Profile
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
import com.example.login.fitur_berita.BeritaTerkini
import com.example.login.fitur_panduan.PanduanBanjir
import com.example.login.fitur_panduan.PanduanKebakaran
import com.example.login.awalan.onBoarding
import com.example.login.lapor.laporBerhasil
import com.example.login.lapor.laporSigma1
import com.example.login.lapor.laporSigma2
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
                    landingPage1(navController)
                }
                composable(Routes.LandingPage2) {
                    landingPage2(navController)
                }
                composable(Routes.Login) {
                    login(navController, authViewModel = viewModel())
                }
                composable(Routes.SignUp) {
                    val authViewModel: AuthViewModel = viewModel()
                    SignUp(navController, authViewModel)
                }
                composable(Routes.Verification) {
                    verification(navController, authViewModel = AuthViewModel())
                }
                composable(Routes.LoginBerhasil) {
                    loginBerhasil(navController)
                }
                composable(Routes.OnBoarding) {
                    onBoarding(navController)
                }
                composable(Routes.Dashboard) {
                    Dashboard(navController)
                }
                composable(Routes.SignUpBerhasil) {
                    signUpBerhasil(navController)
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
                        BeritaDetail(newsId, NewsViewModel(), navController)  // Only pass a valid newsId
                    } else {
                        // Show an error screen or navigate back
                        Text("Error: Invalid news ID")
                    }
                }
                composable(Routes.Profile) {
                    Profile(navController)
                }
                composable(Routes.LaporSigma1) {
                    laporSigma1(navController)
                }
                composable(Routes.LaporSigma2) {
                    laporSigma2(navController)
                }
                composable(Routes.LaporBerhasil) {
                    laporBerhasil(navController)
                }

            }
        }
    }

}
