package com.example.travelapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class WeatherForecast(
	@SerializedName("dt") val dt : Float,
	@SerializedName("main") val main : Main,
	@SerializedName("weather") val weather : List<Weather>,
	@SerializedName("clouds") val clouds : Clouds,
	@SerializedName("wind") val wind : Wind,
	@SerializedName("visibility") val visibility : Int,
	@SerializedName("pop") val pop : Double,
	@SerializedName("rain") val rain : Rain,
	@SerializedName("sys") val sys : Sys,
	@SerializedName("dt_txt") val dt_txt : String
) : Parcelable