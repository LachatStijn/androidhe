package com.example.travelapp.ui.weather.main.viewmodel


import android.util.Log
import androidx.lifecycle.*
import com.example.travelapp.data.model.Country
import com.example.travelapp.data.model.WeatherForecast
import com.example.travelapp.data.model.WeatherResponse
import com.example.travelapp.data.repository.WeatherRepository
import com.github.mikephil.charting.data.Entry
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class WeatherViewModel(private var weatherRepository : WeatherRepository) : ViewModel() {

    //create Livedata
    private val _weatherForecasts = MutableLiveData<WeatherResponse>()
    val weatherForecasts : LiveData<WeatherResponse>
        get() =  _weatherForecasts

    private val _showDetailsOfForecast = MutableLiveData<WeatherForecast>()
    val showDetailsOfForecast: LiveData<WeatherForecast>
        get() = _showDetailsOfForecast


    fun getWeatherForecast(city: String, units: String, lang:String = "nl" ,appid : String = "c70d717f68f450b5579a197a111b7573"){
        viewModelScope.launch {
            var wait = weatherRepository.getWeatherForecastForCity(city, units, lang, appid)
            _weatherForecasts.value = wait
        }
    }

    fun onForecastClicked(wf : WeatherForecast){
        _showDetailsOfForecast.value = wf
    }

    fun onForecastDetailsShowed() {
        _showDetailsOfForecast.value = null
    }


}