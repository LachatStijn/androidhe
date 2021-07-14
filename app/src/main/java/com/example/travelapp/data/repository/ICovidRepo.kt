package com.example.travelapp.data.repository

import com.example.travelapp.data.model.HistoricalResponse

interface ICovidRepo<out A> : IRepository<A>  {
    suspend fun getCovidInfoPerCountry(country: String) : A
    suspend fun getHistoricalInfoPerCountry(country : String) : HistoricalResponse
}