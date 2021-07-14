package com.example.travelapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.travelapp.data.entity.forecast.ForecastEntity

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(forecasts : List<ForecastEntity>)

    @Query("SELECT * FROM forecast")
    fun getForecasts() : LiveData<List<ForecastEntity>>

    @Query("SELECT * FROM forecast WHERE forecast_city_fk = :city")
    fun getForecastByCity(city: Int) : LiveData<ForecastEntity>
}