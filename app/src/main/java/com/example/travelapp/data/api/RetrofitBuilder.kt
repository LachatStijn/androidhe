package com.example.travelapp.data.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {


    private const val BASE_URL_COUNTRIES = "https://restcountries.eu/rest/v2/"
    // X-CSCAPI-KEY = WnlDQ29mcU9UMnJEYWVHMnNHQ0dpa1NQaVF2U3E0ZHM1enpYMjV6dw== (Header)
    private const val BASE_URL_CITIES = "https://api.countrystatecity.in/v1/countries/"
    //api key openweather c70d717f68f450b5579a197a111b7573
    private const val BASE_URL_WEATHER = "https://api.openweathermap.org/data/2.5/"
    private const val BASE_URL_COVID = "https://disease.sh/v3/covid-19/"

    val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    val client : OkHttpClient = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
    }.build()

    private fun getRetrofitCountries(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_COUNTRIES)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private fun getRetrofitWeather(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_WEATHER)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private fun getRetrofitCovid(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_COVID)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private fun getRetrofitCities(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_CITIES)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val apiServiceCovid : ApiService = getRetrofitCovid().create(ApiService::class.java)
    val apiServiceCities : ApiService = getRetrofitCities().create(ApiService::class.java)
    val apiServiceWeather : ApiService = getRetrofitWeather().create(ApiService::class.java)
    val apiServiceCountries : ApiService = getRetrofitCountries().create(ApiService::class.java)


}