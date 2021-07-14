package com.example.travelapp.data.api

class ApiHelper(private val apiService: ApiService) {

    //open api REST COUNTRIES
    suspend fun getCountries() = apiService.getCountries()

    //open api ...
    suspend fun getCitiesByCountry(country: String) = apiService.getCitiesByCountry(country)

    //open api OpenWeather
    suspend fun getForecast(city: String, units:String, lang:String ,appid: String) = apiService.getForecast(city, units, lang,appid)

    suspend fun getCovidInfoPerCountry(country : String) = apiService.getNewCovidInformation(country)

    suspend fun getHistoricalInfoPerCountry(country : String) =  apiService.getHistoricalInfo(country)
}