package com.example.travelapp.data.repository


import androidx.lifecycle.LiveData
import com.example.travelapp.data.database.localDatabase.LocalWeatherDataSource
import com.example.travelapp.data.database.remoteDatabase.OnlineWeatherDataSource
import com.example.travelapp.data.entity.forecast.ForecastEntity
import com.example.travelapp.data.model.WeatherResponse

class WeatherRepository(private val localWeatherDataSource: LocalWeatherDataSource, private val onlineWeatherDataSource: OnlineWeatherDataSource) : IWeatherRepo<WeatherResponse> {
    override suspend fun getWeatherForecastForCity(city: String, units: String, lang:String ,appid: String) : WeatherResponse {
        return onlineWeatherDataSource.getForecast(city, units, lang ,appid)
    }

    override suspend fun getAll(): WeatherResponse {
        TODO("Not yet implemented")
    }

    override suspend fun insertMultiple(forecasts: List<ForecastEntity>) {
        localWeatherDataSource.insertMultiple(forecasts)
    }

    override fun removeSelected(forecasts: List<Long>) {
        localWeatherDataSource.removeSelected(forecasts)
    }

    override fun getForecastsByCity(city_fk: Long): LiveData<List<ForecastEntity>> {
        return localWeatherDataSource.getForecastsByCity(city_fk)
    }

    override fun removeByCity(city: Long) {
        localWeatherDataSource.removeForecastsByCity(city)
    }

}