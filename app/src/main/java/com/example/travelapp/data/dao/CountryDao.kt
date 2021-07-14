package com.example.travelapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.travelapp.data.entity.country.CountryEntity

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countries : List<CountryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(country : CountryEntity) : Long

    @Transaction
    @Query("SELECT * FROM country")
    fun getCountries() : LiveData<List<CountryEntity>>

    @Transaction
    @Query("SELECT * FROM country WHERE country_name = :name")
    fun getCountryByName(name : String) : LiveData<CountryEntity>
}