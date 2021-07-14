package com.example.travelapp.data.entity.country

import androidx.room.Embedded
import androidx.room.Relation
import com.example.travelapp.data.entity.covidHistorical.CovidHistoricalEntity


data class CountryCovidHistorical(
    @Embedded val country : CountryEntity,
    @Relation(
        parentColumn = "countryId",
        entityColumn = "countryFk"
    )
    val covidHistorical : CovidHistoricalEntity
)
