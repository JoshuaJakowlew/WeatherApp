package com.example.weatherapp.owm.api

import android.os.Debug
import android.util.Log
import com.example.weatherapp.owm.dataclasses.Forecast
import com.example.weatherapp.owm.dataclasses.Response
import retrofit2.Call

class OwmService {
    private val retrofit = RetrofitClient.getClient()
    private val owmApi = retrofit.create(OwmAPI::class.java)

    suspend fun getForecast(lat: Double, lon: Double): Response =
        owmApi.getForecast(lat, lon)
}