package com.example.travelapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegionalBloc(
    @SerializedName("acronym") val acronym : String,
    @SerializedName("name") val name : String,
    @SerializedName("otherAcronyms") val otherAcronyms : List<String>,
    @SerializedName("otherNames") val otherNames : List<String>
) : Parcelable