package com.example.travelapp.data.entity.covid

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deaths")
data class CovidDeathsEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "deaths_id")
    val caseId : Int,
    @ColumnInfo(name = "deaths_date")
    val date : String,
    @ColumnInfo(name = "deaths_amount")
    val number : Float,
    @ColumnInfo(name = "cases_covidHistorical_fk")
    val covidHistoricalFk : Int
)