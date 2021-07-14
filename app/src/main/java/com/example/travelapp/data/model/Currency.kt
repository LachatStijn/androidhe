package com.example.travelapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Currency (
    @SerializedName("code") val code : String,
    @SerializedName("name") val name : String,
    @SerializedName("symbol") val symbol : String
) : Parcelable