package com.example.travelapp.ui.cities.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.travelapp.data.api.ApiHelper
import com.example.travelapp.data.database.AppDatabase
import com.example.travelapp.data.repository.CitiesRepository
import com.example.travelapp.ui.cities.main.viewmodel.CitiesViewModel

class CitiesViewModelFactory(private val apiHelper: ApiHelper, private val database: AppDatabase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CitiesViewModel::class.java)){
            return CitiesViewModel(CitiesRepository(apiHelper), database) as T
        }
        throw IllegalArgumentException("unknown class name")
    }
}