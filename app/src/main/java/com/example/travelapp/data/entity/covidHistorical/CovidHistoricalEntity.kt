package com.example.travelapp.data.entity.covidHistorical

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "historical")
@Parcelize
data class CovidHistoricalEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "historical_id")
    val covidHistoricalId : Long,
    @ColumnInfo(name = "historical_country_fk")
    val countryFk : Long
) : Parcelable

