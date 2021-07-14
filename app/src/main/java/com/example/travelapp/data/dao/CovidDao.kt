package com.example.travelapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.travelapp.data.entity.covid.CovidCasesEntity
import com.example.travelapp.data.entity.covid.CovidDeathsEntity
import com.example.travelapp.data.entity.covid.CovidInfoEntity
import com.example.travelapp.data.entity.covidHistorical.CovidHistoricalEntity

@Dao
interface CovidDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllInfo(covidInfo : List<CovidInfoEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCases(cases : List<CovidCasesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDeaths(deaths : List<CovidDeathsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllHistoricalInfo(historicalInfo : List<CovidHistoricalEntity>)

    @Query("SELECT * FROM covid")
    fun getCovidInfo() : LiveData<List<CovidInfoEntity>>

    @Transaction
    @Query("SELECT * FROM historical")
    fun getHistoricalInfo() : LiveData<List<CovidHistoricalEntity>>

    @Query("SELECT * FROM covid WHERE covid_country_fk = :country")
    fun getCovidInfoFromCountry(country : Int) : LiveData<CovidInfoEntity>

    @Transaction
    @Query("SELECT * FROM HISTORICAL WHERE historical_country_fk = :country")
    fun getHistoricalInfoFromCountry(country: Int) : LiveData<CovidHistoricalEntity>

}