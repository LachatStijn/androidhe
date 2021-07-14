package com.example.travelapp.ui.options.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.travelapp.data.api.ApiHelper
import com.example.travelapp.data.repository.CountriesRepository
import com.example.travelapp.ui.options.main.viewmodels.OptionsViewModel
import com.example.travelapp.ui.overview.main.viewmodels.CountriesViewModel
import java.lang.IllegalArgumentException

class OptionsViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(OptionsViewModel::class.java)){
            return OptionsViewModel() as T
        }
        throw IllegalArgumentException("unknown class name")
    }
}