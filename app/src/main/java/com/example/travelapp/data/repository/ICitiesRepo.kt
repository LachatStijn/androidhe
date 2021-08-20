package com.example.travelapp.data.repository

import com.example.travelapp.data.entity.city.CityEntity

interface ICitiesRepo<out A> : IRepository<A> {
    suspend fun getCitiesByCountry(country: Long) : A
    suspend fun insertAll(cities : List<CityEntity>)
}