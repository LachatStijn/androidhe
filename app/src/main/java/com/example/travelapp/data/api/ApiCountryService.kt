package com.example.travelapp.data.api


import com.example.travelapp.data.model.Country
import com.example.travelapp.data.model.CountryResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Url

interface ApiCountryService {
    @GET
    suspend fun getCountries(@Url url:String): CountryResponse
    @GET
    suspend fun getAlphaCode(@Url url: String, @HeaderMap headerMap: Map<String, String>): List<Country>
}

private const val BASE_URL_COUNTRIES = "https://countriesnow.space/api/v0.1/"

var client : OkHttpClient = OkHttpClient.Builder().build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL_COUNTRIES)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()

object CountryApi {
    val retrofitService : ApiCountryService by lazy {
        retrofit.create(ApiCountryService::class.java)
    }
}