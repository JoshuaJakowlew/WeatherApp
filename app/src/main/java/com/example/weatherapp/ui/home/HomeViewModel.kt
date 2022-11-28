package com.example.weatherapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.MainActivity
import kotlinx.coroutines.launch
import java.sql.Date
import kotlin.math.roundToInt

class HomeViewModel : ViewModel() {
    private val _location = MutableLiveData<String>().apply { value = "City" }
    val location: LiveData<String> = _location

    private val _temp = MutableLiveData<String>().apply { value = "0" }
    val temp: LiveData<String> = _temp

    private val _feelsLikeTemp = MutableLiveData<String>().apply { value = "0" }
    val feelsLikeTemp: LiveData<String> = _feelsLikeTemp

    private val _date = MutableLiveData<String>().apply { value = "0" }
    val date: LiveData<String> = _date

    private val _wind = MutableLiveData<String>().apply { value = "0" }
    val wind: LiveData<String> = _wind

    private val _pressure = MutableLiveData<String>().apply { value = "0" }
    val pressure: LiveData<String> = _pressure

    private val _humidity = MutableLiveData<String>().apply { value = "0" }
    val humidity: LiveData<String> = _humidity

    private val _weather = MutableLiveData<String>().apply { value = "0" }
    val weather: LiveData<String> = _weather

    private val _clouds = MutableLiveData<String>().apply { value = "0" }
    val clouds: LiveData<String> = _clouds

    private val _visible = MutableLiveData<String>().apply { value = "0" }
    val visible: LiveData<String> = _visible

    fun updateForecast(activity: MainActivity) {
        viewModelScope.launch {
            val res = activity.updateForecast()
            val forecast = if (activity.forecast == null) {
                res.forecasts!!.first()
            } else {
                activity.forecast!!
            }

            val city = res.city

            _location.value = city.name

            val p = activity.preferences

            val temp = forecast.main.temp.roundToInt()
            if (p.tempInKelvin) {
                _temp.value = "${((temp - 273) * 9.0 / 5.0 + 32).roundToInt()}°F"
            } else {
                _temp.value = "${temp - 273}°C"
            }

            val feelsLikeTemp = forecast.main.feelsLike.roundToInt()
            if (p.tempInKelvin) {
                _feelsLikeTemp.value = "${((feelsLikeTemp - 273) * 9.0 / 5.0 + 32).roundToInt()}°F"
            } else {
                _feelsLikeTemp.value = "${feelsLikeTemp - 273}°C"
            }

            val wind = forecast.wind.speed
            if (p.windInMS) {
                _wind.value = "${wind} m/s"
            } else {
                _wind.value = "${(wind / 1000 * 3600).roundToInt()} km/h"
            }

            val pressure = forecast.main.pressure
            if (p.pressureInPascal) {
                _pressure.value = "${pressure} GPa"
            } else {
                _pressure.value = "${(pressure / 1.33322387415).toInt()} mm Hg"
            }

            _humidity.value = "${forecast.main.humidity.toString()} %"

            _weather.value = forecast.weather!!.first().description

            _date.value = Date(forecast.dateUnix.toLong() * 1000).toString()

            _visible.value = "${forecast.visibility.toString()} m"

            _clouds.value = "${forecast.clouds.all} %"
        }
    }
}