package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PreferencesViewModel : ViewModel() {
    private val _preferences = MutableLiveData<Preferences>().apply { value = Preferences() }
    val preferences: LiveData<Preferences> = _preferences

    fun updateTemp(inKelvin: Boolean) {
        val p = _preferences.value
        p!!.tempInKelvin = inKelvin
        _preferences.value = p
    }

    fun updateSpeed(inMs: Boolean) {
        val p = _preferences.value
        p!!.windInMS = inMs
        _preferences.value = p
    }

    fun updatePressure(inPascal: Boolean) {
        val p = _preferences.value
        p!!.pressureInPascal = inPascal
        _preferences.value = p
    }
}