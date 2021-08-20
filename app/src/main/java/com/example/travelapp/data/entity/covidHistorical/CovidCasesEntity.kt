package com.example.travelapp.data.entity.covidHistorical

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "cases", foreignKeys = [
        androidx.room.ForeignKey(
            onDelete = CASCADE,
            entity = CovidHistoricalEntity::class,
            parentColumns = ["historical_id"],
            childColumns = ["cases_covidHistorical_fk"]
        )])
@Parcelize
data class CovidCasesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cases_id")
    val caseId : Long,
    @ColumnInfo(name = "cases_date")
    val date : String,
    @ColumnInfo(name = "cases_amount")
    val number : Float,
    @ColumnInfo(name = "cases_covidHistorical_fk")
    val covidHistoricalFk : Long
) : Parcelable
