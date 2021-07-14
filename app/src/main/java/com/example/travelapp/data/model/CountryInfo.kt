package com.example.travelapp.data.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CountryInfo (

	@SerializedName("_id") val _id : Int,
	@SerializedName("iso2") val iso2 : String,
	@SerializedName("iso3") val iso3 : String,
	@SerializedName("lat") val lat : Double,
	@SerializedName("long") val long : Int,
	@SerializedName("flag") val flag : String
) : Parcelable