package com.example.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                    Login(navController)
                }
                composable(Routes.SignUp) {
                    SignUp(navController)
                }
                composable(Routes.Verification) {
                    Verification(navController)
                }
                composable(Routes.LoginBerhasil) {
                    LoginBerhasil(navController)
                }
                composable(Routes.OnBoarding) {
                    OnBoarding(navController)
                }
            }

        }
    }
}
