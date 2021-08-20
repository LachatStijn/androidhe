package com.example.travelapp.data.database.localDatabase

import com.example.travelapp.data.dao.CountryDao
import com.example.travelapp.data.entity.country.CountryEntity

class LocalCountryDataSource(private val countryLocalDatasource: CountryDao) {
    suspend fun insertAll(countries : List<CountryEntity>) = countryLocalDatasource.insertAll(countries)
    fun getCountry(name : String) = countryLocalDatasource.getCountryByName(name)
    fun getAll() = countryLocalDatasource.getCountries()
    fun updateCountry(country : CountryEntity) = countryLocalDatasource.updateCountry(country)
}