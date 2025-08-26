package com.example.login

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GeocodingViewModel(application: Application) : AndroidViewModel(application) {
    private val apiKey = "ea46319fc11e3d9732a2d9485c339518" // API key kamu
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    var cityName by mutableStateOf("Loading...")
        private set
    var temperature by mutableStateOf("-")
        private set
    var weatherCondition by mutableStateOf("Loading...")
        private set

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val weatherApi = retrofit.create(OpenWeatherApi::class.java)

    @SuppressLint("MissingPermission")
    fun loadWeather() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                fetchWeather(it.latitude, it.longitude)
            } ?: run {
                cityName = "Lokasi tidak ditemukan"
            }
        }
    }

    private fun fetchWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                val response = weatherApi.getCurrentWeather(lat, lon, "metric", apiKey)
                cityName = response.name
                temperature = "${response.main.temp}°C"
                weatherCondition = response.weather.firstOrNull()?.description ?: "-"
            } catch (e: Exception) {
                cityName = "Error"
                weatherCondition = "Cuaca tidak diketahui"
                Log.d(e.message,"Eror")
            }
        }
    }
}
