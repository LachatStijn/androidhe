package com.example.travelapp.data.entity.covid

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cases")
data class CovidCasesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cases_id")
    val caseId : Int,
    @ColumnInfo(name = "cases_date")
    val date : String,
    @ColumnInfo(name = "cases_amount")
    val number : Float,
    @ColumnInfo(name = "cases_covidHistorical_fk")
    val covidHistoricalFk : Int
)
