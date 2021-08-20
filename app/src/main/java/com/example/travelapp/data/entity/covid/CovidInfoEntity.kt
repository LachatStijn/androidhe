package com.example.travelapp.data.entity.covid

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.travelapp.data.Converters.DateConverter
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity(tableName = "covid")
@Parcelize
data class CovidInfoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "covid_id")
    val covidId : Long,
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
    val countryFk : Long,
    @ColumnInfo(name="covid_info_update")
    @TypeConverters(DateConverter::class)
    val update : Date
) : Parcelable
