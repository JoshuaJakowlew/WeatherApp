package com.example.weatherapp.owm.dataclasses

import com.squareup.moshi.Json

data class Coord(@Json(name = "lon")
                 val lon: Double = 0.0,
                 @Json(name = "lat")
                 val lat: Double = 0.0)