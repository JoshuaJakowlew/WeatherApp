package com.example.weatherapp

import com.squareup.moshi.Json

data class Preferences(
    var tempInKelvin: Boolean = false,
    var windInMS: Boolean = false,
    var pressureInPascal: Boolean = false
)