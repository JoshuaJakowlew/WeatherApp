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

    private val _location = MutableLiveData<String>().apply {
        value = "City"
    }
    val location: LiveData<String> = _location

    private val _temp = MutableLiveData<String>().apply {
        value = "0"
    }
    val temp: LiveData<String> = _temp

    fun updateForecast(activity: MainActivity) {
        viewModelScope.launch {
            val res = activity.updateForecast()

            _location.value = res.city.name
            _temp.value = res.forecasts!!.first().main.temp.roundToInt().toString()
        }
    }
}