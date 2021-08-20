package com.example.travelapp.data.database.remoteDatabase

import com.example.travelapp.data.api.ApiWeatherService
import com.example.travelapp.modules.Endpoints

class OnlineWeatherDataSource(private val apiService : ApiWeatherService) {

    suspend fun getForecast(city : String, units : String, lang : String, appid : String) = apiService.getForecast(Endpoints.WEATHER_BASE_URL,city, units, lang, appid)
}