package com.example.travelapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City (
	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("coord") val coord : Coord,
	@SerializedName("country") val country : String,
	@SerializedName("population") val population : Int,
	@SerializedName("timezone") val timezone : Int,
	@SerializedName("sunrise") val sunrise : Int,
	@SerializedName("sunset") val sunset : Int
) : Parcelable