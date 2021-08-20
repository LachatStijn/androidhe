package com.example.travelapp.ui.options.main.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelapp.data.entity.country.CountryEntity
import com.example.travelapp.data.repository.CountriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OptionMenuViewModel(private val countryRepository: CountriesRepository) : ViewModel() {

    lateinit var newCountry : CountryEntity

    fun getAlpha2Code(country : CountryEntity){
        var url = "https://restcountries.eu/rest/v2/name/${country.name}?fields=alpha2Code"
        var map = HashMap<String, String>()
        map.put("X-CSCAPI-KEY","WnlDQ29mcU9UMnJEYWVHMnNHQ0dpa1NQaVF2U3E0ZHM1enpYMjV6dw==")
        viewModelScope.launch {
            var result = countryRepository.getAlphaCode(url, map)
            newCountry = CountryEntity(country.countryId, country.name, result[0].alpha2Code)
            withContext(Dispatchers.IO) {
                countryRepository.updateCountry(newCountry)
            }
        }

    }
}