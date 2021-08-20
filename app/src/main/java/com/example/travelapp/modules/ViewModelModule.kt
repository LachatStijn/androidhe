package com.example.travelapp.modules

import com.example.travelapp.ui.cities.main.viewmodel.CitiesViewModel
import com.example.travelapp.ui.covid.main.viewmodel.CovidViewModel
import com.example.travelapp.ui.options.main.viewmodels.OptionMenuViewModel
import com.example.travelapp.ui.overview.main.viewmodels.CountriesViewModel
import com.example.travelapp.ui.weather.main.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CountriesViewModel(get(), get())}
    viewModel { CitiesViewModel(get()) }
    viewModel { WeatherViewModel(get()) }
    viewModel { CovidViewModel(get()) }
    viewModel { OptionMenuViewModel(get()) }
}