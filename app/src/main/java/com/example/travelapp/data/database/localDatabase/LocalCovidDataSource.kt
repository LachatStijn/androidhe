package com.example.travelapp.data.database.localDatabase

import com.example.travelapp.data.dao.CovidDao
import com.example.travelapp.data.entity.covid.CovidInfoEntity
import com.example.travelapp.data.entity.covidHistorical.CovidCasesEntity
import com.example.travelapp.data.entity.covidHistorical.CovidDeathsEntity
import com.example.travelapp.data.entity.covidHistorical.CovidHistoricalEntity

class LocalCovidDataSource(private val covidLocalDatabase: CovidDao) {
    suspend fun insertAllCovidCases(covidCases : List<CovidCasesEntity>) = covidLocalDatabase.insertAllCases(covidCases)
    suspend fun insertAllDeaths(covidDeaths : List<CovidDeathsEntity>) = covidLocalDatabase.insertAllDeaths(covidDeaths)

    suspend fun insertOneHistoricalInfo(historicalInfo: CovidHistoricalEntity) = covidLocalDatabase.insertOneHistoricalInfo(historicalInfo)
    suspend fun insertOneCovidInfo(covidInfo : CovidInfoEntity) = covidLocalDatabase.insertOneCovidInfo(covidInfo)

    fun getCovidInfo() = covidLocalDatabase.getCovidInfo()
    fun getHistoricalInfo() = covidLocalDatabase.getHistoricalInfo()
    fun getCovidInfoFromCountry(country : Long) = covidLocalDatabase.getCovidInfoFromCountry(country)
    fun getHistoricalInfoFromCountry(country: Long) = covidLocalDatabase.getHistoricalInfoFromCountry(country)

    fun getCasesAndDeaths(historical: Long) = covidLocalDatabase.getCasesAndDeaths(historical)

    fun removeCovidInfo(country : Long) = covidLocalDatabase.removeCovidInfoByCountry(country)
    fun removeHistoricalCascade(country : Long) = covidLocalDatabase.removeHistoricalCascade(country)
}