package com.example.travelapp.data.database.localDatabase

import com.example.travelapp.data.dao.CityDao
import com.example.travelapp.data.entity.city.CityEntity

class LocalCityDataSource(private val cityLocalDatabase: CityDao) {

    fun getAllByCountry(country : Long) = cityLocalDatabase.getAllByCountry(country)
    fun getAll() = cityLocalDatabase.getAll()

    suspend fun insertAll(cities : List<CityEntity>) = cityLocalDatabase.insertAll(cities)
}