package com.example.weatherapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.MainActivity
import com.example.weatherapp.owm.api.OwmService
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.sql.Date
import kotlin.math.roundToInt

class HomeViewModel : ViewModel() {
    private val owmService = OwmService()

    private val _location = MutableLiveData<String>().apply { value = "City" }
    val location: LiveData<String> = _location

    private val _temp = MutableLiveData<String>().apply { value = "0" }
    val temp: LiveData<String> = _temp

    private val _date = MutableLiveData<String>().apply { value = "0" }
    val date: LiveData<String> = _date

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
            _temp.value = forecast.main.temp.roundToInt().toString()
            _date.value = Date(forecast.dateUnix.toLong() * 1000).toString()
        }
    }
}