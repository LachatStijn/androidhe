package com.example.travelapp.data.repository

import androidx.lifecycle.LiveData
import com.example.travelapp.data.entity.forecast.ForecastEntity

interface IWeatherRepo<out A> : IRepository<A> {
    suspend fun getWeatherForecastForCity(city: String, country: String, lang:String ,appid: String) : A
    suspend fun insertMultiple(forecasts : List<ForecastEntity>)
    fun removeSelected(forecasts : List<Long>)
    fun removeByCity(city : Long)
    fun getForecastsByCity(city_fk : Long) : LiveData<List<ForecastEntity>>
}