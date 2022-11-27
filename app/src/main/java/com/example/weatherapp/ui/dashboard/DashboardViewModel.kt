package com.example.weatherapp.ui.dashboard

import android.widget.Toast
import androidx.lifecycle.*
import com.example.weatherapp.MainActivity
import com.example.weatherapp.owm.dataclasses.Forecast
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.math.roundToInt

class DashboardViewModel : ViewModel() {

    private val _forecasts = MutableLiveData<List<Forecast>>().apply {
        value = listOf()
    }
    val forecasts: LiveData<List<Forecast>> = _forecasts

    private val _forecastReady = MutableLiveData<Boolean>().apply {
        value = false
    }
    val forecastReady: LiveData<Boolean> = _forecastReady

    fun updateForecast(activity: MainActivity) {
        viewModelScope.launch {
            val res = activity.updateForecast()

            _forecastReady.value = true

            _forecasts.value = res.forecasts!!
                .filterIndexed { i, _ -> i % 8 == 0 }
        }
    }
}