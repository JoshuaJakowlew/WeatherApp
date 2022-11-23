package com.example.weatherapp.owm.dataclasses

import com.squareup.moshi.Json

data class Sys(@Json(name = "pod")
               val pod: String = "")