package com.example.travelapp.ui.overview.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelapp.data.entity.city.CityEntity
import com.example.travelapp.data.entity.country.CountryEntity
import com.example.travelapp.data.model.CountryResponse
import com.example.travelapp.data.repository.CitiesRepository
import com.example.travelapp.data.repository.CountriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountriesViewModel(
    private val countriesRepository: CountriesRepository,
    private val citiesRepository: CitiesRepository) : ViewModel() {


    val localCountries = countriesRepository.getAllLocal()

    private var _navigateToShowOptions = MutableLiveData<CountryEntity>()
    val navigateToShowOptions: LiveData<CountryEntity>
        get() = _navigateToShowOptions


    fun getCountries(){
        viewModelScope.launch {
            var response = withContext(Dispatchers.IO){
                countriesRepository.getAll()
            }
            handleResponse(response)
        }
    }

    private suspend fun handleResponse(countryResponse: CountryResponse) {
        var countryEntities = countryResponse.data.map { country ->
            CountryEntity(0, country.name, "")
        }

        var listId = withContext(Dispatchers.IO){
            countriesRepository.insertAll(countryEntities)
        }

        var countryCities = listOf<CityEntity>()

        countryResponse.data.forEachIndexed { id, element ->
            countryCities += element.cities.map { city ->
                CityEntity(0, city, listId[id])
            }
        }

        withContext(Dispatchers.IO){
            citiesRepository.insertAll(countryCities)
        }

    }


    fun onCountryClicked(country : CountryEntity){
        _navigateToShowOptions.value = country
    }

    fun onCountryOptionsNavigated() {
        _navigateToShowOptions.value = null
    }


}