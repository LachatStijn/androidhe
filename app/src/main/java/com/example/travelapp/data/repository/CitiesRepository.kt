package com.example.travelapp.data.repository

import androidx.lifecycle.LiveData
import com.example.travelapp.data.database.localDatabase.LocalCityDataSource
import com.example.travelapp.data.entity.city.CityEntity

class CitiesRepository (private val localCityDataSource: LocalCityDataSource) : ICitiesRepo<LiveData<List<CityEntity>>> {
    override suspend fun getCitiesByCountry(country: Long): LiveData<List<CityEntity>> {
        return localCityDataSource.getAllByCountry(country)
    }

    override suspend fun getAll(): LiveData<List<CityEntity>> {
        return localCityDataSource.getAll()
    }

    override suspend fun insertAll(cities: List<CityEntity>) {
        localCityDataSource.insertAll(cities)
    }
}