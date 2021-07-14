package com.example.travelapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Timeline (
	@ColumnInfo(name = "timeline_cases")
	@SerializedName("cases") val cases : @RawValue Any?,
	@SerializedName("deaths") val deaths : @RawValue Any?,
	@SerializedName("recovered") val recovered : @RawValue Any?

) : Parcelable