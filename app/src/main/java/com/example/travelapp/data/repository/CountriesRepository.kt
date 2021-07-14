package com.example.travelapp.data.repository

import com.example.travelapp.data.api.ApiHelper
import com.example.travelapp.data.model.Country

class CountriesRepository(private val apiHelper: ApiHelper) : ICountryRepo<List<Country>> {
    override suspend fun getAll(): List<Country> {
        return apiHelper.getCountries();
    }

}