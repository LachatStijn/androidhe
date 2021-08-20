package com.example.travelapp.data.repository

import androidx.lifecycle.LiveData
import com.example.travelapp.data.database.localDatabase.LocalCountryDataSource
import com.example.travelapp.data.database.remoteDatabase.OnlineCountryDataSource
import com.example.travelapp.data.entity.country.CountryEntity
import com.example.travelapp.data.model.Country
import com.example.travelapp.data.model.CountryResponse

class CountriesRepository(private val localCountryDataSource: LocalCountryDataSource, private val onlineCountryDataSource: OnlineCountryDataSource) : ICountryRepo<CountryResponse> {
    override suspend fun getAll(): CountryResponse {
        return onlineCountryDataSource.getAll()
    }

    override fun getAllLocal(): LiveData<List<CountryEntity>> {
        return localCountryDataSource.getAll()
    }

    override suspend fun insertAll(countries: List<CountryEntity>): List<Long> {
        return localCountryDataSource.insertAll(countries)
    }

    override suspend fun getAlphaCode(url: String, headerMap: Map<String, String>): List<Country> {
        return onlineCountryDataSource.getAlphaCode(url, headerMap)
    }

    override fun updateCountry(country: CountryEntity) {
        return localCountryDataSource.updateCountry(country)
    }

    override fun getCountry(name: String) : CountryEntity {
        return localCountryDataSource.getCountry(name)
    }

}