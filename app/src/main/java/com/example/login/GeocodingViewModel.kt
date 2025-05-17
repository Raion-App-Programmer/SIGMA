<<<<<<< HEAD
package com.example.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GeocodingViewModel : ViewModel() {
    private val apiKey = "YOUR_API_KEY"

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://maps.googleapis.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(GeocodingApiService::class.java)

    fun getCityName(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            try {
                val response = apiService.getLocationName("$latitude,$longitude", apiKey).execute()
                if (response.isSuccessful) {
                    val result = response.body()?.results?.firstOrNull()?.formatted_address
                    Log.d("Geocoding", "Location: $result")
                } else {
                    Log.e("Geocoding", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("Geocoding", "Exception: ${e.message}")
            }
        }
    }
}
=======
package com.example.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GeocodingViewModel : ViewModel() {
    private val apiKey = "YOUR_API_KEY"

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://maps.googleapis.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(GeocodingApiService::class.java)

    fun getCityName(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            try {
                val response = apiService.getLocationName("$latitude,$longitude", apiKey).execute()
                if (response.isSuccessful) {
                    val result = response.body()?.results?.firstOrNull()?.formatted_address
                    Log.d("Geocoding", "Location: $result")
                } else {
                    Log.e("Geocoding", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("Geocoding", "Exception: ${e.message}")
            }
        }
    }
}
>>>>>>> d62e06663ef865d66006b307e4d8c9f26225b180
