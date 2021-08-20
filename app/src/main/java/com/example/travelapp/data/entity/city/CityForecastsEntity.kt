package com.example.travelapp.data.entity.city

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.example.travelapp.data.entity.forecast.ForecastEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityForecastsEntity(
    @Embedded val city : CityEntity,
    @Relation(
        parentColumn = "city_id",
        entityColumn = "forecast_city_fk"
    )
    val forecasts : List<ForecastEntity>

) : Parcelable
