package com.example.travelapp.data.repository

import androidx.lifecycle.LiveData
import com.example.travelapp.data.entity.covid.CovidInfoEntity
import com.example.travelapp.data.entity.covidHistorical.CovidCasesEntity
import com.example.travelapp.data.entity.covidHistorical.CovidDeathsEntity
import com.example.travelapp.data.entity.covidHistorical.CovidHistoricalDeathsCases
import com.example.travelapp.data.entity.covidHistorical.CovidHistoricalEntity
import com.example.travelapp.data.model.CovidPerCountryResponse
import com.example.travelapp.data.model.HistoricalResponse

interface ICovidRepo<out A> : IRepository<A>  {
    suspend fun getNewCovidInformation(country: String) : A
    suspend fun getHistoricalInfo(country : String) : HistoricalResponse
    suspend fun getAllRemote() : List<CovidPerCountryResponse>


    suspend fun insertAllCovidCases(covidCases : List<CovidCasesEntity>) : List<Long>
    suspend fun insertAllDeaths(covidDeaths : List<CovidDeathsEntity>) : List<Long>

    suspend fun insertOneHistoricalInfo(historicalInfo: CovidHistoricalEntity) : Long
    suspend fun insertOneCovidInfo(covidInfo : CovidInfoEntity) : Long

    fun getCovidInfo() : LiveData<List<CovidInfoEntity>>
    fun getHistoricalInfo() : LiveData<List<CovidHistoricalEntity>>
    fun getCovidInfoFromCountry(country : Long) : LiveData<CovidInfoEntity>
    fun getHistoricalInfoFromCountry(country: Long) : LiveData<CovidHistoricalEntity>

    fun getCasesAndDeaths(historical: Long) : LiveData<CovidHistoricalDeathsCases>

    fun removeCovidInfo(country : Long)
    fun removeHistoricalCascade(historical : Long)

}