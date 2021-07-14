package com.example.travelapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.travelapp.data.entity.city.CityEntity

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cities : List<CityEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(city : CityEntity)

    @Transaction
    @Query("SELECT * FROM city")
    fun getCities() : LiveData<List<CityEntity>>

    @Transaction
    @Query("SELECT * FROM city WHERE city_country_fk = :country")
    fun getCityByCountry(country : Int) : LiveData<List<CityEntity>>
}