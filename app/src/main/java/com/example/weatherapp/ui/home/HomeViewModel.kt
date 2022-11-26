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

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _textDate = MutableLiveData<String>().apply {
        value = "Current date..."
    }
    val textDate: LiveData<String> = _textDate

    private val _textCity = MutableLiveData<String>().apply {
        value = "City..."
    }
    val textCity: LiveData<String> = _textCity

    private val _textTemp = MutableLiveData<String>().apply {
        value = "Temperature..."
    }
    val textTemp: LiveData<String> = _textTemp

    fun updateForecast(activity: MainActivity) {
        viewModelScope.launch {
            val res = activity.updateForecast()

            _text.value = res.city.coord.toString()

            val city = res.city.name
            val temp = res.forecasts!!.first().main.temp - 273
            val date = Date(res.forecasts.first().dateUnix.toLong() * 1000)

            _textCity.value = city
            _textDate.value = date.toString()
            _textTemp.value = temp.roundToInt().toString()
        }
    }
}