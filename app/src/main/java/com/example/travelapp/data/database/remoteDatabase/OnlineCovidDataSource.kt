package com.example.travelapp.data.database.remoteDatabase

import com.example.travelapp.data.api.ApiCovidService
import com.example.travelapp.modules.Endpoints

class OnlineCovidDataSource(private val apiService : ApiCovidService) {

    suspend fun getNewCovidInformation(country: String) = apiService.getNewCovidInformation(Endpoints.COVID_BASE_URL+country)
    suspend fun getHistoricalInfo(country: String) = apiService.getHistoricalInfo(Endpoints.COVID_BASE_URL+country)
    suspend fun getAllCovidInfo() = apiService.getAllCovidInof(Endpoints.COVID_BASE_URL+"countries/")
}