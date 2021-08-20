package com.example.travelapp.data.repository
import androidx.lifecycle.LiveData
import com.example.travelapp.data.database.localDatabase.LocalCovidDataSource
import com.example.travelapp.data.database.remoteDatabase.OnlineCovidDataSource
import com.example.travelapp.data.entity.covid.CovidInfoEntity
import com.example.travelapp.data.entity.covidHistorical.CovidCasesEntity
import com.example.travelapp.data.entity.covidHistorical.CovidDeathsEntity
import com.example.travelapp.data.entity.covidHistorical.CovidHistoricalDeathsCases
import com.example.travelapp.data.entity.covidHistorical.CovidHistoricalEntity
import com.example.travelapp.data.model.CovidPerCountryResponse
import com.example.travelapp.data.model.HistoricalResponse

class CovidRepo(private val localCovidDataSource: LocalCovidDataSource, private val onlineCovidDataSource: OnlineCovidDataSource) : ICovidRepo<CovidPerCountryResponse> {

    //api

    override suspend fun getNewCovidInformation(country: String): CovidPerCountryResponse {
        return onlineCovidDataSource.getNewCovidInformation(country)
    }

    override suspend fun getHistoricalInfo(country: String): HistoricalResponse {
        return onlineCovidDataSource.getHistoricalInfo(country)
    }





    //localInsert

    override suspend fun insertAllCovidCases(covidCases: List<CovidCasesEntity>) : List<Long> {
        return localCovidDataSource.insertAllCovidCases(covidCases)
    }

    override suspend fun insertAllDeaths(covidDeaths: List<CovidDeathsEntity>) : List<Long> {
        return localCovidDataSource.insertAllDeaths(covidDeaths)
    }

    override suspend fun insertOneCovidInfo(covidInfo: CovidInfoEntity): Long {
        return localCovidDataSource.insertOneCovidInfo(covidInfo)
    }


    override suspend fun insertOneHistoricalInfo(historicalInfo: CovidHistoricalEntity): Long {
        return localCovidDataSource.insertOneHistoricalInfo(historicalInfo)
    }

    //local get

    override fun getCovidInfo(): LiveData<List<CovidInfoEntity>> {
        return localCovidDataSource.getCovidInfo()
    }

    override fun getHistoricalInfo(): LiveData<List<CovidHistoricalEntity>> {
        return localCovidDataSource.getHistoricalInfo()
    }

    override fun getCovidInfoFromCountry(country: Long): LiveData<CovidInfoEntity> {
        return localCovidDataSource.getCovidInfoFromCountry(country)
    }

    override fun getHistoricalInfoFromCountry(country: Long): LiveData<CovidHistoricalEntity> {
        return localCovidDataSource.getHistoricalInfoFromCountry(country)
    }

    override fun getCasesAndDeaths(historical: Long): LiveData<CovidHistoricalDeathsCases> {
        return localCovidDataSource.getCasesAndDeaths(historical)
    }

    override fun removeCovidInfo(country: Long) {
        localCovidDataSource.removeCovidInfo(country)
    }

    override fun removeHistoricalCascade(country: Long) {
        localCovidDataSource.removeHistoricalCascade(country)
    }

    override suspend fun getAllRemote(): List<CovidPerCountryResponse> {
        return onlineCovidDataSource.getAllCovidInfo()
    }

    override suspend fun getAll(): CovidPerCountryResponse {
        TODO("Not yet implemented")
    }


}