package com.example.travelapp

import android.app.Application
import com.example.travelapp.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class LaunchTravelApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@LaunchTravelApp)
            modules(
                networkModule,
                viewModelModule,
                repositoryModule,
                daoModule,
                dataSourceModule
            )
        }
    }
}