package com.example.travelapp.data.entity.country

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "country")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "country_id")
    val countryId : Int,
    @ColumnInfo(name = "country_name")
    val name : String,
    @ColumnInfo(name = "country_acronym")
    val alpha2Code : String,
    @ColumnInfo(name = "country_capital")
    val capital : String,
    @ColumnInfo(name = "country_region")
    val region : String,
    @ColumnInfo(name = "country_population")
    val population : Float,
    @ColumnInfo(name = "country_flag_uri")
    val flag : String,
    @ColumnInfo(name = "country_name_nl")
    val countryNameNl : String,

) : Parcelable
