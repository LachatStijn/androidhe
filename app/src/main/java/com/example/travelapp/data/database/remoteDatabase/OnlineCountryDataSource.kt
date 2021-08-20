package com.example.travelapp.data.database.remoteDatabase

import com.example.travelapp.data.api.ApiCountryService
import com.example.travelapp.modules.Endpoints

class OnlineCountryDataSource(private val apiService : ApiCountryService) {
    suspend fun getAll() = apiService.getCountries(Endpoints.COUNTRY_BASE_URL)
    suspend fun getAlphaCode(url : String, headerMap: Map<String, String>) = apiService.getAlphaCode(url, headerMap)
}
