package com.example.weatherapp.owm.dataclasses

import com.squareup.moshi.Json

data class City(@Json(name = "country")
                val country: String = "",
                @Json(name = "coord")
                val coord: Coord,
                @Json(name = "sunrise")
                val sunrise: Int = 0,
                @Json(name = "timezone")
                val timezone: Int = 0,
                @Json(name = "sunset")
                val sunset: Int = 0,
                @Json(name = "name")
                val name: String = "",
                @Json(name = "id")
                val id: Int = 0,
                @Json(name = "population")
                val population: Int = 0)