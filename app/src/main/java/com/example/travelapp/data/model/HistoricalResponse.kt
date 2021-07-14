package com.example.travelapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class HistoricalResponse(
	@PrimaryKey(autoGenerate = true)
	val id : Long,
	@SerializedName("country") val country : String,
	@SerializedName("province") val province : List<String>,
	@SerializedName("timeline") val timeline : Timeline
) : Parcelable