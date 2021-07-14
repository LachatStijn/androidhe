package com.example.travelapp.data.entity.forecast

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast")
data class ForecastEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "forecast_id")
    val forecastId : Int,
    @ColumnInfo(name = "forecast_dt")
    val dt : Float,
    @ColumnInfo(name = "forecast_temp")
    val temp : Double,
    @ColumnInfo(name = "forecast_feels_like")
    val feelsLike : Double,
    @ColumnInfo(name = "forecast_temp_min")
    val tempMin : Double,
    @ColumnInfo(name = "forecast_temp_max")
    val tempMax : Double,
    @ColumnInfo(name = "forecast_pressure")
    val pressure : Double,
    @ColumnInfo(name = "forecast_humidity")
    val humidity : Int,
    @ColumnInfo(name = "forecast_description")
    val description : String,
    @ColumnInfo(name = "forecast_wind_speed")
    val windSpeed : Double,
    @ColumnInfo(name = "forecast_visibility")
    val visibility : Int,
    @ColumnInfo(name = "forecast_dt_text")
    val dtText : String,
    @ColumnInfo(name = "forecast_city_fk")
    val cityFk : Int
)
