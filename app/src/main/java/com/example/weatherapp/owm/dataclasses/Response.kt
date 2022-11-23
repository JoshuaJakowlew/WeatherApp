package com.example.weatherapp.owm.dataclasses

import com.squareup.moshi.Json

data class Response(@Json(name = "city")
                    val city: City,
                    @Json(name = "cnt")
                    val forecastCount: Int = 0,
                    @Json(name = "list")
                    val forecasts: List<Forecast>?)