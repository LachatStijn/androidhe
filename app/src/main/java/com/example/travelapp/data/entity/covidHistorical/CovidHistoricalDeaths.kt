package com.example.travelapp.data.entity.covidHistorical

import androidx.room.Embedded
import androidx.room.Relation
import com.example.travelapp.data.entity.covid.CovidDeathsEntity
import com.example.travelapp.data.entity.covidHistorical.CovidHistoricalEntity

data class CovidHistoricalDeaths(
    @Embedded val historical : CovidHistoricalEntity,
    @Relation(
        parentColumn = "covidHistoricalId",
        entityColumn = "covidHistoricalFk"
    )
    val cases : List<CovidDeathsEntity>
)
