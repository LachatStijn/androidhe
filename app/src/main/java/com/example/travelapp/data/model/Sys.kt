package com.example.travelapp.data.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Sys (

	@SerializedName("pod") val pod : String
) : Parcelable