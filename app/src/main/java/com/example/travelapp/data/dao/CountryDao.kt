package com.example.travelapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.travelapp.data.entity.country.CountryEntity

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countries : List<CountryEntity>) : List<Long>

    @Query("SELECT * FROM country where country_name = :name")
    fun getCountryByName(name : String) : CountryEntity

    @Update
    fun updateCountry(country : CountryEntity)

    @Query("SELECT * FROM country")
    fun getCountries() : LiveData<List<CountryEntity>>


}