package com.example.weatherapp.owm.dataclasses

import com.squareup.moshi.Json

data class Forecast(@Json(name = "dt")
                    val dateUnix: Int = 0,
                    @Json(name = "pop")
                    val pop: Double = 0.0,
                    @Json(name = "visibility")
                    val visibility: Int = 0,
                    @Json(name = "dt_txt")
                    val dateText: String = "",
                    @Json(name = "weather")
                    val weather: List<WeatherItem>?,
                    @Json(name = "main")
                    val main: Main,
                    @Json(name = "clouds")
                    val clouds: Clouds,
                    @Json(name = "sys")
                    val sys: Sys,
                    @Json(name = "wind")
                    val wind: Wind
)