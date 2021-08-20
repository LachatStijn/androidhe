package com.example.travelapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryResponse(
    @SerializedName("data") val data : List<Country>
) : Parcelable
