package com.example.travelapp.data.entity.covid

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "covid")
data class CovidInfoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "covid_id")
    val covidId : Int,
    @ColumnInfo(name = "covid_updated")
    val updated : Float,
    @ColumnInfo(name = "covid_iso2")
    val iso2 : String,
    @ColumnInfo(name = "covid_total_cases")
    val casesAmount : Float,
    @ColumnInfo(name = "covid_total_cases_today")
    val todayCasesAmount : Float,
    @ColumnInfo(name = "covid_total_deaths")
    val deathsAmount : Float,
    @ColumnInfo(name = "covid_total_deaths_today")
    val todayDeathsAmount : Float,
    @ColumnInfo(name = "covid_recovered")
    val recovered : Float,
    @ColumnInfo(name = "covid_recovered_today")
    val todayRecovered : Float,
    @ColumnInfo(name = "covid_critical")
    val critical : Float,
    @ColumnInfo(name = "covid_total_tests")
    val tests : Float,
    @ColumnInfo(name = "covid_country_fk")
    val countryFk : Int
)
