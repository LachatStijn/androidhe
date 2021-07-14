package com.example.travelapp.data.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class WeatherResponse (
	@SerializedName("cod") val cod : Int,
	@SerializedName("message") val message : Int,
	@SerializedName("cnt") val cnt : Int,
	@SerializedName("list") val list : List<WeatherForecast>,
	@SerializedName("city") val city : City
) : Parcelable