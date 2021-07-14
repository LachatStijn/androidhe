package com.example.travelapp.data.repository


import com.example.travelapp.data.api.ApiHelper
import com.example.travelapp.data.model.WeatherResponse

class WeatherRepository(private val apiHelper: ApiHelper) : IWeatherRepo<WeatherResponse> {
    override suspend fun getWeatherForecastForCity(city: String, units: String, lang:String ,appid: String) : WeatherResponse {
        return apiHelper.getForecast(city, units, lang ,appid)
    }

    override suspend fun getAll(): WeatherResponse {
        TODO("Not yet implemented")
    }

}