package com.example.travelapp.data.api

import com.example.travelapp.data.model.WeatherResponse
import com.example.travelapp.modules.Endpoints
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiWeatherService {
    @GET
    suspend fun getForecast(
        @Url url : String,
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("appid") appid: String
    ) : WeatherResponse
}



private val retrofit = Retrofit.Builder()
    .baseUrl(Endpoints.WEATHER_BASE_URL)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()

object WeatherApi {
    val retrofitService : ApiWeatherService by lazy {
        retrofit.create(ApiWeatherService::class.java)
    }
}