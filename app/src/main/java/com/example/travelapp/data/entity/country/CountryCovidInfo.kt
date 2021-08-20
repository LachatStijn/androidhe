package com.example.travelapp.data.entity.country

import androidx.room.Embedded
import androidx.room.Relation
import com.example.travelapp.data.entity.covid.CovidInfoEntity

data class CountryCovidInfo(
    @Embedded val country : CountryEntity,
    @Relation(
        parentColumn = "country_id",
        entityColumn = "covid_country_fk"
    )
    val covidInfo : CovidInfoEntity
)
