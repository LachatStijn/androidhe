package com.example.travelapp.data.entity.covidHistorical

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "deaths", foreignKeys = [
ForeignKey(
    onDelete = CASCADE,
    entity = CovidHistoricalEntity::class,
    parentColumns = ["historical_id"],
    childColumns = ["deaths_covidHistorical_fk"]
)]
)
@Parcelize
data class CovidDeathsEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "deaths_id")
    val caseId : Long,
    @ColumnInfo(name = "deaths_date")
    val date : String,
    @ColumnInfo(name = "deaths_amount")
    val number : Float,
    @ColumnInfo(name = "deaths_covidHistorical_fk")
    val covidHistoricalFk : Long
) : Parcelable