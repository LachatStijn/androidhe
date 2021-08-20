package com.example.travelapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.travelapp.data.entity.covid.CovidInfoEntity
import com.example.travelapp.data.entity.covidHistorical.CovidCasesEntity
import com.example.travelapp.data.entity.covidHistorical.CovidDeathsEntity
import com.example.travelapp.data.entity.covidHistorical.CovidHistoricalDeathsCases
import com.example.travelapp.data.entity.covidHistorical.CovidHistoricalEntity

@Dao
interface CovidDao {

    @Query("DELETE FROM covid where covid_country_fk = :country")
    fun removeCovidInfoByCountry(country : Long)

    @Query("DELETE FROM historical where historical_country_fk = :country")
    fun removeHistoricalCascade(country: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCases(cases : List<CovidCasesEntity>) : List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDeaths(deaths : List<CovidDeathsEntity>) : List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneHistoricalInfo(historicalInfo: CovidHistoricalEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneCovidInfo(covidInfo : CovidInfoEntity) : Long


    @Query("SELECT * FROM cases where cases_covidHistorical_fk = :historical")
    fun getCases(historical : Long) : LiveData<List<CovidCasesEntity>>

    @Query("SELECT * FROM deaths where deaths_covidHistorical_fk = :historical")
    fun getDeaths(historical : Long) : LiveData<List<CovidDeathsEntity>>

    @Transaction
    @Query("SELECT * FROM historical where historical_id = :historical")
    fun getCasesAndDeaths(historical: Long) : LiveData<CovidHistoricalDeathsCases>


    @Query("SELECT * FROM covid")
    fun getCovidInfo() : LiveData<List<CovidInfoEntity>>


    @Query("SELECT * FROM historical")
    fun getHistoricalInfo() : LiveData<List<CovidHistoricalEntity>>

    @Query("SELECT * FROM covid WHERE covid_country_fk = :country")
    fun getCovidInfoFromCountry(country : Long) : LiveData<CovidInfoEntity>

    @Query("SELECT * FROM HISTORICAL WHERE historical_country_fk = :country")
    fun getHistoricalInfoFromCountry(country: Long) : LiveData<CovidHistoricalEntity>

}