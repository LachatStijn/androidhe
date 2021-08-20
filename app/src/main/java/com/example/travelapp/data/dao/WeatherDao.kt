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

    @Query("DELETE FROM forecast where forecast_id in (:list)")
    fun removeSelected(list : List<Long>)

    @Query("DELETE FROM forecast where forecast_city_fk = :fk")
    fun removeAllForecastByCityId(fk : Long)

    @Query("SELECT * FROM forecast where forecast_city_fk = :city_fk limit 1")
    fun getOneForecastByCity(city_fk: Long) : ForecastEntity?

    @Query("SELECT * FROM forecast")
    fun getForecasts() : LiveData<List<ForecastEntity>>

    @Query("SELECT * FROM forecast where forecast_city_fk = :city_fk")
    fun getForecastsByCity(city_fk : Long) : LiveData<List<ForecastEntity>>

}