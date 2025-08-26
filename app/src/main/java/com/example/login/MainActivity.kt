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
import com.example.login.admin.DetailPengajuanScreen
import com.example.login.admin.NewsConfirmationScreen
import com.example.login.fitur_panduan.PanduanBanjir
import com.example.login.fitur_panduan.PanduanKebakaran
import com.example.login.awalan.onBoarding
import com.example.login.daftar.verificationTerisi
import com.example.login.fitur_lapor.LaporanViewModel
import com.example.login.fitur_panduan.P3
import com.example.login.lapor.laporBerhasil
import com.example.login.lapor.laporSigma1
//import com.example.login.lapor.laporSigma1
import com.example.login.profile.UbahProfilViewModel
import com.example.mytestsigma.ui.theme.Dashboard
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import PanduanGempa
import Profile
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.app.NotificationCompat
import com.example.login.dashboard.NotifikasiPage
import com.example.login.lapor.laporSigma2
import com.example.login.lapor.laporSigma3
import com.example.login.profile.ubahProfile
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.DocumentChange

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private val authViewModel by viewModels<AuthViewModel>()

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Init Firebase
        FirebaseApp.initializeApp(this)
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        listenToStatusChange()

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val laporanViewModel: LaporanViewModel = viewModel()
            val geoViewModel: GeocodingViewModel = viewModel()

            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()      // aman dari status bar
                    .navigationBarsPadding()  // aman dari nav bar bawah
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = Routes.Profile,
                    modifier = Modifier.padding(innerPadding)
                ) {
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
                    composable(Routes.VerificationFilled) {
                        verificationTerisi(navController)
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
                        if (!newsId.isNullOrBlank()) {
                            BeritaDetail(newsId, NewsViewModel(), navController)
                        } else {
                            Text("Error: Invalid news ID")
                        }
                    }
                    composable(
                        "DetailPengajuan/{newsId}",
                        arguments = listOf(navArgument("newsId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val newsId = backStackEntry.arguments?.getString("newsId")
                        if (!newsId.isNullOrBlank()) {
                            DetailPengajuanScreen(newsId = newsId, navController = navController)
                        }
                    }
                    composable(Routes.Profile) {
                        Profile(navController)
                    }
                    composable(Routes.UbahProfile) {
                        ubahProfile(
                            navController,
                            ubahProfilViewModel = UbahProfilViewModel()
                        )
                    }
                    composable(Routes.LaporSigma1) {
                        laporSigma1(navController, laporanViewModel)
                    }
                    composable(Routes.LaporSigma2) {
                        laporSigma2(navController, laporanViewModel)
                    }
                    composable(Routes.LaporSigma3) {
                        laporSigma3(navController, laporanViewModel)
                    }
                    composable(Routes.LaporBerhasil) {
                        laporBerhasil(navController)
                    }
                    composable(Routes.P3) {
                        P3(navController)
                    }
                    composable(Routes.PanduanGempa) {
                        PanduanGempa(navController)
                    }
                    composable(Routes.KonfirmasiBerita) {
                        NewsConfirmationScreen(navController, NewsViewModel())
                    }
                    composable(Routes.notifikasipage) {
                        NotifikasiPage(navController)
                    }
                    composable(
                        "emergency_services_screen/{latitude}/{longitude}/{cityName}/{isUrban}",
                        arguments = listOf(
                            navArgument("latitude") { type = NavType.FloatType },
                            navArgument("longitude") { type = NavType.FloatType },
                            navArgument("cityName") { type = NavType.StringType },
                            navArgument("isUrban") { type = NavType.BoolType }
                        )
                    ) { backStackEntry ->
                        val latitude = backStackEntry.arguments?.getFloat("latitude")
                        val longitude = backStackEntry.arguments?.getFloat("longitude")
                        val cityName = backStackEntry.arguments?.getString("cityName")
                        val isUrban = backStackEntry.arguments?.getBoolean("isUrban") ?: false

                        if (isUrban) {
                            panggilSigma1(navController, latitude, longitude, cityName)
                        } else {
                            panggilSigma2(navController, geoViewModel)
                        }
                    }
                }
            }
        }
    }


    private fun listenToStatusChange() {
        val uid = auth.currentUser?.uid
        if (uid == null) {
            Log.w("DEBUG_LISTEN", "UID is null, cannot listen to laporan")
            return
        }

        Log.d("DEBUG_LISTEN", "Start listening to laporan where uid = $uid")

        firestore.collection("laporan")
            .whereEqualTo("uid", uid)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.e("DEBUG_LISTEN", "Error while listening: ${e.message}")
                    return@addSnapshotListener
                }

                if (snapshots == null) {
                    Log.w("DEBUG_LISTEN", "Snapshot is null")
                    return@addSnapshotListener
                }

                Log.d("DEBUG_LISTEN", "Snapshot size: ${snapshots.size()}")

                for (change in snapshots.documentChanges) {
                    Log.d("DEBUG_LISTEN", "Document change detected: ${change.type}")

                    if (change.type == DocumentChange.Type.MODIFIED) {
                        val docId = change.document.id
                        val newStatus = change.document.getString("status")

                        Log.d("DEBUG_LISTEN", "Doc ID: $docId, New Status: $newStatus")

                        if (!newStatus.isNullOrEmpty()) {
                            showNotification(
                                "Status Diperbarui",
                                "Status laporan Anda kini: $newStatus"
                            )
                            Log.d("DEBUG_LISTEN", "Notification triggered for status: $newStatus")
                        } else {
                            Log.w("DEBUG_LISTEN", "Status field is null or empty")
                        }
                    }
                }
            }
    }

    private fun showNotification(title: String, message: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.logo_notif)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Status Notifikasi",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
    }
}
