package com.example.travelapp.modules

import com.example.travelapp.data.database.localDatabase.LocalCityDataSource
import com.example.travelapp.data.database.localDatabase.LocalCountryDataSource
import com.example.travelapp.data.database.localDatabase.LocalCovidDataSource
import com.example.travelapp.data.database.localDatabase.LocalWeatherDataSource
import com.example.travelapp.data.database.remoteDatabase.OnlineCountryDataSource
import com.example.travelapp.data.database.remoteDatabase.OnlineCovidDataSource
import com.example.travelapp.data.database.remoteDatabase.OnlineWeatherDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    //country
    single { LocalCountryDataSource(get()) }
    single { OnlineCountryDataSource(get()) }
    //city
    single { LocalCityDataSource(get()) }
    //weather
    single { LocalWeatherDataSource(get())}
    single { OnlineWeatherDataSource(get()) }
    //covid
    single { LocalCovidDataSource(get()) }
    single { OnlineCovidDataSource(get()) }
}