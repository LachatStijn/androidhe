package com.example.travelapp.data.entity.covidHistorical

import androidx.room.Embedded
import androidx.room.Relation

data class CovidHistoricalDeathsCases(
    @Embedded val historical : CovidHistoricalEntity,
    @Relation(
        parentColumn = "historical_id",
        entityColumn = "deaths_covidHistorical_fk"
    )
    val deaths : List<CovidDeathsEntity>,
    @Relation(
        parentColumn = "historical_id",
        entityColumn = "cases_covidHistorical_fk"
    )
    val cases : List<CovidCasesEntity>
)
