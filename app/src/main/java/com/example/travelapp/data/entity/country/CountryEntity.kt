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
    val countryId : Long,
    @ColumnInfo(name = "country_name")
    val name : String,
    @ColumnInfo(name = "iso_2")
    val iso_2 : String,
) : Parcelable
