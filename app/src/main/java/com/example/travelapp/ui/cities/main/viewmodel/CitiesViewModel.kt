package com.example.travelapp.ui.cities.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelapp.data.database.AppDatabase
import com.example.travelapp.data.entity.city.CityEntity
import com.example.travelapp.data.repository.CitiesRepository

class CitiesViewModel(
    private val citiesRepository: CitiesRepository,
    private val database: AppDatabase) : ViewModel() {

    //create LiveData
    private val _cities = MutableLiveData<List<CityEntity>>()
    private val _actualDataSet = MutableLiveData<List<CityEntity>>()
    val cities : LiveData<List<CityEntity>>
        get() = _cities



    private var _navigateToWeatherDetails = MutableLiveData<CityEntity>()
    val navigateToWeatherDetails: LiveData<CityEntity>
        get() = _navigateToWeatherDetails



    fun getCitiesByCountry(country: Int): LiveData<List<CityEntity>> {
        return database.cityDao().getCityByCountry(country)
    }



    fun filterData(filterValue : CharSequence?){
        if(filterValue!!.isNotBlank()){
            var data = _actualDataSet.value?.filter { city ->
                city.name.contains(filterValue!!)
            }
            _cities.value = data
        }else {
            _cities.value = _actualDataSet.value
        }
    }

    fun onCityClicked(city : CityEntity){
        _navigateToWeatherDetails.value = city
    }

    fun onCityWeatherDetailsNavigated() {
        _navigateToWeatherDetails.value = null
    }


}