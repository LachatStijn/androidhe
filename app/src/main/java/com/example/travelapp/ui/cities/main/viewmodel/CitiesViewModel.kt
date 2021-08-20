package com.example.travelapp.ui.cities.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelapp.data.entity.city.CityEntity
import com.example.travelapp.data.entity.country.CountryEntity
import com.example.travelapp.data.repository.CitiesRepository
import kotlinx.coroutines.launch

class CitiesViewModel constructor(
    private val citiesRepository: CitiesRepository) : ViewModel() {




    var actualDataSet = MutableLiveData<List<CityEntity>>()

    lateinit var countryCitiesEntity : LiveData<List<CityEntity>>

    private var _navigateToWeatherDetails = MutableLiveData<CityEntity>()
    val navigateToWeatherDetails: LiveData<CityEntity>
        get() = _navigateToWeatherDetails



    fun getCitiesByCountry(country: CountryEntity) {
        viewModelScope.launch {
            countryCitiesEntity = citiesRepository.getCitiesByCountry(country.countryId)
        }
    }



    fun filterData(filterValue : CharSequence?){
        if(filterValue!!.isNotBlank()){

            var data = countryCitiesEntity.value?.filter { city ->
                city.name.lowercase().contains(filterValue.toString().lowercase())
            }

            actualDataSet.value = data!!
        }else{
            actualDataSet.value = countryCitiesEntity.value
        }
    }

    fun onCityClicked(city : CityEntity){
        _navigateToWeatherDetails.value = city
    }

    fun onCityWeatherDetailsNavigated() {
        _navigateToWeatherDetails.value = null
    }


}