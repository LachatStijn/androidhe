package com.example.travelapp.ui.weather.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.travelapp.data.api.ApiHelper
import com.example.travelapp.data.repository.WeatherRepository
import com.example.travelapp.ui.weather.main.viewmodel.WeatherViewModel
import java.lang.IllegalArgumentException

class WeatherViewModelFactory(private val apiHelper: ApiHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WeatherViewModel::class.java)){
            return WeatherViewModel(WeatherRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("unknown class name")
    }
}