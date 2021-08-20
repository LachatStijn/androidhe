package com.example.travelapp.modules

import com.example.travelapp.data.database.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val daoModule = module {
    single {AppDatabase.getInstance(androidApplication())}
    single { get<AppDatabase>().countryDao()}
    single { get<AppDatabase>().cityDao()}
    single { get<AppDatabase>().covidDao()}
    single { get<AppDatabase>().weatherDao()}
}