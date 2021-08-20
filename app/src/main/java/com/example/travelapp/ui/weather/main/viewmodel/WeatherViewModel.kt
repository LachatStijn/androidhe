package com.example.travelapp.ui.weather.main.viewmodel


import androidx.lifecycle.*
import com.example.travelapp.data.entity.city.CityEntity
import com.example.travelapp.data.entity.forecast.ForecastEntity
import com.example.travelapp.data.model.*
import com.example.travelapp.data.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class WeatherViewModel(private var weatherRepository : WeatherRepository) : ViewModel() {

    //create Livedata


    private val cityEntity = MutableLiveData<CityEntity>()
    fun setCountry(city : CityEntity){
        cityEntity.value = city
    }

    val localForecasts : LiveData<List<ForecastEntity>> = Transformations.switchMap(cityEntity) {
        cityEntity -> weatherRepository.getForecastsByCity(cityEntity.cityId)
    }

    private val _weatherForecasts = MutableLiveData<List<WeatherForecast>>()
    val weatherForecasts : LiveData<List<WeatherForecast>>
        get() =  _weatherForecasts

    private val _showDetailsOfForecast = MutableLiveData<WeatherForecast>()
    val showDetailsOfForecast: LiveData<WeatherForecast>
        get() = _showDetailsOfForecast

    private val _cardView = MutableLiveData<Integer>()
    val cardView: LiveData<Integer>
        get() = _cardView




    fun getWeatherForecast(city: String, units: String, lang:String = "nl" ,appid : String = "c70d717f68f450b5579a197a111b7573"){
        viewModelScope.launch {
            var result = weatherRepository.getWeatherForecastForCity(city, units, lang, appid)
            var entities = result.list.map { wf ->
                ForecastEntity(0,wf.dt, wf.main.temp, wf.main.feels_like, wf.main.temp_min,
                    wf.main.temp_max, wf.main.pressure, wf.main.humidity, wf.weather[0].description,
                    wf.wind.speed, wf.visibility, wf.dt_txt, cityEntity.value!!.cityId, Calendar.getInstance().time)
            }
            insertWeatherEntities(entities)
            _weatherForecasts.value = result.list
        }
    }

    private fun insertWeatherEntities(list : List<ForecastEntity>){
        viewModelScope.launch {
            var result = viewModelScope.launch(Dispatchers.IO) {
                weatherRepository.insertMultiple(list)
            }
            result.join()
        }
    }

    fun removeByCity() {
        viewModelScope.launch {
            var result = viewModelScope.launch(Dispatchers.IO) {
                weatherRepository.removeByCity(cityEntity.value!!.cityId)
            }
            result.join()
        }
    }




    fun handleDatabaseCall(forecasts: List<ForecastEntity>){
        var result = forecasts.map {
            WeatherForecast(it.dt, Main(it.temp, it.feelsLike, it.tempMin, it.tempMax, it.pressure, 0, 0,it.humidity, 0.0), listOf(
                Weather(0,"", it.description, "")
            ), Clouds(0), Wind(it.windSpeed, 0,0.0), it.visibility, 0.0,Rain(.0), Sys(""), it.dtText)
        }

        _weatherForecasts.value = result
    }



    fun onForecastClicked(wf : WeatherForecast, cv : Integer){
        _showDetailsOfForecast.value = wf
        _cardView.value = cv
    }

    fun onForecastDetailsShowed() {
        _showDetailsOfForecast.value = null
    }


}