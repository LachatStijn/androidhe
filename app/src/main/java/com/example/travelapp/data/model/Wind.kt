package com.example.travelapp.data.model
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Wind (
	@SerializedName("speed") val speed : Double,
	@SerializedName("deg") val deg : Int,
	@SerializedName("gust") val gust : Double
) : Parcelable