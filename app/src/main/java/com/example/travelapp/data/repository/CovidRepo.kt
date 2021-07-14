package com.example.travelapp.data.repository
import com.example.travelapp.data.api.ApiHelper
import com.example.travelapp.data.model.CovidPerCountryResponse
import com.example.travelapp.data.model.HistoricalResponse

class CovidRepo(private val apiHelper: ApiHelper) : ICovidRepo<CovidPerCountryResponse> {
    override suspend fun getCovidInfoPerCountry(country: String): CovidPerCountryResponse {
        return apiHelper.getCovidInfoPerCountry(country)
    }

    override suspend fun getAll(): CovidPerCountryResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getHistoricalInfoPerCountry(country: String): HistoricalResponse {
        return apiHelper.getHistoricalInfoPerCountry(country)
    }
}