package com.example.travelapp.data.entity.country

import androidx.room.Embedded
import androidx.room.Relation
import com.example.travelapp.data.entity.city.CityEntity

data class CountryCitiesEntity(
    @Embedded val country : CountryEntity,
    @Relation(
        parentColumn = "countryId",
        entityColumn = "countryFk"
    )
    val cities : List<CityEntity>
)
