package com.example.travelapp.data.entity.city

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "city")
data class CityEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "city_id")
    val cityId : Long,
    @ColumnInfo(name = "city_name")
    val name : String,
    @ColumnInfo(name  = "city_country_fk")
    val countryFk : Long,
) : Parcelable
