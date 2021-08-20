package com.example.travelapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.travelapp.data.entity.city.CityEntity
import com.example.travelapp.data.entity.city.CityForecastsEntity

//create class dao

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cities : List<CityEntity>)

    @Transaction
    @Query("SELECT * FROM city")
    fun getAll() : LiveData<List<CityEntity>>

    @Transaction
    @Query("SELECT * FROM city WHERE city_country_fk = :country")
    fun getAllByCountry(country : Long) : LiveData<List<CityEntity>>

    @Transaction
    @Query("SELECT * FROM city where city_name = :cityName")
    fun getCityByName(cityName : String) : CityForecastsEntity?
}