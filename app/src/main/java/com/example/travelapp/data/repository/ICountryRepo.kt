package com.example.travelapp.data.repository

import androidx.lifecycle.LiveData
import com.example.travelapp.data.entity.country.CountryEntity
import com.example.travelapp.data.model.Country

interface ICountryRepo<out A> : IRepository<A> {
    fun getAllLocal() : LiveData<List<CountryEntity>>
    suspend fun insertAll(countries : List<CountryEntity>) : List<Long>
    fun updateCountry(country : CountryEntity)
    fun getCountry(name : String) : CountryEntity
    suspend fun getAlphaCode(url : String, headerMap: Map<String, String>) : List<Country>
}