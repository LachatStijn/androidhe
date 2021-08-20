package com.example.travelapp.modules

import com.example.travelapp.data.api.ApiCountryService
import com.example.travelapp.data.api.ApiCovidService
import com.example.travelapp.data.api.ApiWeatherService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideClient() }
    single { provideRetrofit("https://countriesnow.space/api/v0.1/", get()) }
    single { get<Retrofit>().create(ApiCountryService::class.java) }
    single { get<Retrofit>().create(ApiWeatherService::class.java) }
    single { get<Retrofit>().create(ApiCovidService::class.java) }

}

private fun provideClient() = OkHttpClient.Builder().build()

private fun provideRetrofit(BASE_URL:String, okHttpClient: OkHttpClient) : Retrofit =
    Retrofit.Builder()
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

object Endpoints {
    const val COUNTRY_BASE_URL : String = "https://countriesnow.space/api/v0.1/countries"
    const val COVID_BASE_URL : String = "https://disease.sh/v3/covid-19/"
    const val WEATHER_BASE_URL : String = "https://api.openweathermap.org/data/2.5/forecast"
}