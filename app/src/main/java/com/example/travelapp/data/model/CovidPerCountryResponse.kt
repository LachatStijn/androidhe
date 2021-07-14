package com.example.travelapp.data.model
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize



@Parcelize
data class CovidPerCountryResponse (
	@SerializedName("updated") val updated : Float,
	@SerializedName("country") val country : String,
	@SerializedName("countryInfo") val countryInfo : CountryInfo,
	@SerializedName("cases") val cases : Int,
	@SerializedName("todayCases") val todayCases : Int,
	@SerializedName("deaths") val deaths : Int,
	@SerializedName("todayDeaths") val todayDeaths : Int,
	@SerializedName("recovered") val recovered : Int,
	@SerializedName("todayRecovered") val todayRecovered : Int,
	@SerializedName("active") val active : Int,
	@SerializedName("critical") val critical : Int,
	@SerializedName("casesPerOneMillion") val casesPerOneMillion : Int,
	@SerializedName("deathsPerOneMillion") val deathsPerOneMillion : Int,
	@SerializedName("tests") val tests : Int,
	@SerializedName("testsPerOneMillion") val testsPerOneMillion : Int,
	@SerializedName("population") val population : Int,
	@SerializedName("continent") val continent : String,
	@SerializedName("oneCasePerPeople") val oneCasePerPeople : Int,
	@SerializedName("oneDeathPerPeople") val oneDeathPerPeople : Int,
	@SerializedName("oneTestPerPeople") val oneTestPerPeople : Int,
	@SerializedName("undefined") val undefined : Int,
	@SerializedName("activePerOneMillion") val activePerOneMillion : Double,
	@SerializedName("recoveredPerOneMillion") val recoveredPerOneMillion : Double,
	@SerializedName("criticalPerOneMillion") val criticalPerOneMillion : Double
) : Parcelable