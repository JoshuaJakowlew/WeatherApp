package com.example.weatherapp.owm.dataclasses

import com.squareup.moshi.Json

data class Wind(@Json(name = "deg")
                val deg: Int = 0,
                @Json(name = "speed")
                val speed: Double = 0.0,
                @Json(name = "gust")
                val gust: Double = 0.0)