package com.example.travelapp.data.repository

interface IWeatherRepo<out A> : IRepository<A> {
    suspend fun getWeatherForecastForCity(city: String, country: String, lang:String ,appid: String) : A
}