package com.example.travelapp.data.entity.covidHistorical

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "historical")
data class CovidHistoricalEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "historical_id")
    val covidHistoricalId : Int,
    @ColumnInfo(name = "historical_country_fk")
    val countryFk : Int
)

