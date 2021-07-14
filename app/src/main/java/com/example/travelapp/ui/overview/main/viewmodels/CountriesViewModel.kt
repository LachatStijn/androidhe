package com.example.travelapp.ui.overview.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelapp.data.database.AppDatabase
import com.example.travelapp.data.entity.country.CountryEntity
import com.example.travelapp.data.model.Country
import com.example.travelapp.data.repository.CountriesRepository

class CountriesViewModel(
    private val countriesRepository: CountriesRepository,
    val database : AppDatabase) : ViewModel() {

    //create live data
    private var _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>>
        get() = _countries

    private var _navigateToShowOptions = MutableLiveData<CountryEntity>()
    val navigateToShowOptions: LiveData<CountryEntity>
        get() = _navigateToShowOptions

    val databaseCountries = database.countryDao().getCountries()


    fun onCountryClicked(country : CountryEntity){
        _navigateToShowOptions.value = country
    }

    fun onCountryOptionsNavigated() {
        _navigateToShowOptions.value = null
    }


}