package com.example.travelapp.data.api

import com.example.travelapp.data.model.CovidPerCountryResponse
import com.example.travelapp.data.model.HistoricalResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiCovidService {

    @GET
    suspend fun getNewCovidInformation(@Url url : String) : CovidPerCountryResponse

    @GET
    suspend fun getAllCovidInof(@Url url : String) : List<CovidPerCountryResponse>

    @GET
    suspend fun getHistoricalInfo(@Url url : String) : HistoricalResponse
}

private const val BASE_URL_COVID = "https://disease.sh/v3/covid-19/"


private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL_COVID)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()

object CovidApi {
    val retrofitService : ApiWeatherService by lazy {
        retrofit.create(ApiWeatherService::class.java)
    }
}