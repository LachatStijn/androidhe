package com.example.travelapp.data.database.localDatabase

import com.example.travelapp.data.dao.WeatherDao
import com.example.travelapp.data.entity.forecast.ForecastEntity

class LocalWeatherDataSource(private val weatherLocalDatasource: WeatherDao) {
    suspend fun insertMultiple(forecasts : List<ForecastEntity>) = weatherLocalDatasource.insertAll(forecasts)
    fun removeSelected(forecasts : List<Long>) = weatherLocalDatasource.removeSelected(forecasts)
    fun removeForecastsByCity(city : Long) = weatherLocalDatasource.removeAllForecastByCityId(city)
    fun getForecastsByCity(city_fk : Long) = weatherLocalDatasource.getForecastsByCity(city_fk)
}