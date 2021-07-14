package com.example.travelapp.ui.covid.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.travelapp.data.api.ApiHelper
import com.example.travelapp.data.repository.CovidRepo
import com.example.travelapp.ui.covid.main.viewmodel.CovidViewModel
import java.lang.IllegalArgumentException

class CovidViewModelFactory (private val apiHelper: ApiHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CovidViewModel::class.java)){
            return CovidViewModel(CovidRepo(apiHelper)) as T
        }
        throw IllegalArgumentException("unknown class name")
    }
}