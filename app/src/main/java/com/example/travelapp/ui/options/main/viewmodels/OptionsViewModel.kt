package com.example.travelapp.ui.options.main.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelapp.data.model.Country

class OptionsViewModel : ViewModel() {

    private var _navigateToCovidInfo = MutableLiveData<Country>()
    val navigateToCovidInfo: LiveData<Country>
        get() = _navigateToCovidInfo

    private var _navigateToWeatherInfo = MutableLiveData<Country>()
    val navigateToWeatherInfo: LiveData<Country>
        get() = _navigateToWeatherInfo

    fun onCovidClicked(country : Country){
        _navigateToCovidInfo.value = country
    }

    fun onCovidInfoNavigated() {
        _navigateToCovidInfo.value = null
    }

    fun onWheaterClicked(country : Country){
        _navigateToWeatherInfo.value = country
    }

    fun onWheaterInfoNavigated() {
        _navigateToWeatherInfo.value = null
    }
}