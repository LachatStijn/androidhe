package com.example.travelapp.data.api



import com.example.travelapp.data.model.*
import retrofit2.http.*

interface ApiService {

    @GET("all")
    suspend fun getCountries(): List<Country>

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("appid") appid: String
    ) : WeatherResponse

    @Headers("X-CSCAPI-KEY: WnlDQ29mcU9UMnJEYWVHMnNHQ0dpa1NQaVF2U3E0ZHM1enpYMjV6dw==")
    @GET("{country}/cities")
    suspend fun getCitiesByCountry(
        @Path("country") country:String) : List<City>

    @GET("countries/{country}")
    suspend fun getNewCovidInformation(
        @Path("country") country: String) : CovidPerCountryResponse

    @GET("historical/{country}")
    suspend fun getHistoricalInfo(
        @Path("country") country: String) : HistoricalResponse
}