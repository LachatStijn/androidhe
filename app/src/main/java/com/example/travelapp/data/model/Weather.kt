package com.example.travelapp.data.model
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Weather (
	@SerializedName("id") val id : Int,
	@SerializedName("main") val main : String,
	@SerializedName("description") val description : String,
	@SerializedName("icon") val icon : String
) : Parcelable