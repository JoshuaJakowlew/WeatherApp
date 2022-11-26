package com.example.weatherapp.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.MainActivity
import com.example.weatherapp.owm.dataclasses.Forecast
import com.jjoe64.graphview.series.DataPoint
import kotlinx.coroutines.launch
import java.sql.Date
import kotlin.math.roundToInt

class NotificationsViewModel : ViewModel() {

    private val _temp = MutableLiveData<Array<DataPoint>>().apply {
        value = arrayOf(
            DataPoint(0.0, 0.0),
            DataPoint(1.0, 0.0),
            DataPoint(2.0, 0.0),
            DataPoint(3.0, 0.0),
            DataPoint(4.0, 0.0),
        )
    }
    val temp: LiveData<Array<DataPoint>> = _temp

    fun updateForecast(activity: MainActivity) {
        viewModelScope.launch {
            val res = activity.updateForecast()

            _temp.value = res.forecasts!!
//                .filterIndexed { i, _ -> i % 8 == 0 }
                .mapIndexed {
                    i, f -> DataPoint(i.toDouble() / 8, f.main.temp)
                }.toTypedArray()
        }
    }
}