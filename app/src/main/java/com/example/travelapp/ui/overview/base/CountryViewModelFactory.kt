package com.example.travelapp.ui.overview.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.travelapp.data.api.ApiHelper
import com.example.travelapp.data.database.AppDatabase
import com.example.travelapp.data.repository.CountriesRepository
import com.example.travelapp.ui.overview.main.viewmodels.CountriesViewModel

class CountryViewModelFactory(
    private val dataSource : AppDatabase,
    private val apiHelper: ApiHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CountriesViewModel::class.java)){
            return CountriesViewModel(CountriesRepository(apiHelper), dataSource) as T
        }
        throw IllegalArgumentException("unknown class name")
    }
}