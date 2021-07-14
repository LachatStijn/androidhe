package com.example.travelapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Translation(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    @SerializedName("de") val de : String,
    @SerializedName("es") val es : String,
    @SerializedName("fr") val fr : String,
    @SerializedName("ja") val ja : String,
    @SerializedName("it") val it : String,
    @SerializedName("br") val br : String,
    @SerializedName("pt") val pt : String,
    @SerializedName("nl") val nl : String,
    @SerializedName("hr") val hr : String,
    @SerializedName("fa") val fa : String
) : Parcelable
