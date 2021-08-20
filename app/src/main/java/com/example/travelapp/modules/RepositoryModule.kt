package com.example.travelapp.modules

import com.example.travelapp.data.repository.CitiesRepository
import com.example.travelapp.data.repository.CountriesRepository
import com.example.travelapp.data.repository.CovidRepo
import com.example.travelapp.data.repository.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { CitiesRepository(get()) }
    factory { CountriesRepository(get(), get()) }
    factory { WeatherRepository(get(), get()) }
    factory { CovidRepo(get(), get()) }
}