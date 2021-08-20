package com.example.travelapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Country(
    val countryId : Int,
    @SerializedName("country")
    val name : String,
    @SerializedName("cities")
    val cities : ArrayList<String>,
    @SerializedName("alpha2Code")
    val alpha2Code : String
): Parcelable
