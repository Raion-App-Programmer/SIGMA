package com.example.login

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingApiService {
    @GET("maps/api/geocode/json")
    fun getLocationName(
        @Query("latlng") latlng: String,
        @Query("key") apiKey: String
    ): Call<GeocodingResponse>
}
data class GeocodingResponse(
    val results: List<Result>,
    val status: String
)

data class Result(
    val formatted_address: String
)
