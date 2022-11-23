package com.example.weatherapp.owm.api

import com.example.weatherapp.owm.dataclasses.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OwmAPI {
    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") key: String = RetrofitClient.KEY
    ): Response
}