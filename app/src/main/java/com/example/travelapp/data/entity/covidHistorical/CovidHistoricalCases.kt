package com.example.travelapp.data.entity.covidHistorical

import androidx.room.Embedded
import androidx.room.Relation
import com.example.travelapp.data.entity.covid.CovidCasesEntity
import com.example.travelapp.data.entity.covidHistorical.CovidHistoricalEntity


data class CovidHistoricalCases(
    @Embedded   val historical : CovidHistoricalEntity,
    @Relation(
        parentColumn = "covidHistoricalId",
        entityColumn = "covidHistoricalFk"
    )
    val cases : List<CovidCasesEntity>
)
