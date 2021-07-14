package com.example.travelapp.ui.covid.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelapp.data.model.CovidPerCountryResponse
import com.example.travelapp.data.model.HistoricalResponse
import com.example.travelapp.data.repository.CovidRepo
import kotlinx.coroutines.launch

class CovidViewModel(private var covidRepo : CovidRepo) : ViewModel() {

    private var _covidInfo = MutableLiveData<CovidPerCountryResponse>()
    val covidInfo: LiveData<CovidPerCountryResponse>
        get() = _covidInfo

    private var _historicalData = MutableLiveData<HistoricalResponse>()
    val historicalData: LiveData<HistoricalResponse>
        get() = _historicalData

    fun getData(country : String){
        getCovidInfo(country)
        getHistoricalData(country)
    }

    private fun getCovidInfo(country : String){
        viewModelScope.launch {
            var result = covidRepo.getCovidInfoPerCountry(country)
            _covidInfo.value = result
        }
    }

    private fun getHistoricalData(country : String){
        viewModelScope.launch {
            var result = covidRepo.getHistoricalInfoPerCountry(country)
            _historicalData.value = result
        }
    }
}