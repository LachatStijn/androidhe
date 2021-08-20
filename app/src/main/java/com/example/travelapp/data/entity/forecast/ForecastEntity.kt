package com.example.travelapp.data.entity.forecast

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.travelapp.data.Converters.DateConverter
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity(tableName = "forecast")
@Parcelize
data class ForecastEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "forecast_id")
    val forecastId : Long,
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
    val pressure : Int,
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
    val cityFk : Long,
    @ColumnInfo(name="forecast_updated")
    @TypeConverters(DateConverter::class)
    val updated : Date

) : Parcelable
