package com.example.travelapp.data.repository

import com.example.travelapp.data.api.ApiHelper
import com.example.travelapp.data.model.City

class CitiesRepository(private val apiHelper: ApiHelper) : ICitiesRepo<List<City>> {
    override suspend fun getCitiesByCountry(country: String): List<City> {
        return apiHelper.getCitiesByCountry(country)
    }

    override suspend fun getAll(): List<City> {
        TODO("Not yet implemented")
    }
}