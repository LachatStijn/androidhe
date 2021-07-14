package com.example.travelapp.data.entity.city

import androidx.room.Embedded
import androidx.room.Relation
import com.example.travelapp.data.entity.forecast.ForecastEntity

data class CityForecastsEntity(
    @Embedded val city : CityEntity,
    @Relation(
        parentColumn = "cityId",
        entityColumn = "cityFk"
    )
    val forecasts : List<ForecastEntity>

)
