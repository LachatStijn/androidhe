package com.example.travelapp.data.repository

interface ICitiesRepo<out A> : IRepository<A> {
    suspend fun getCitiesByCountry(country: String) : A
}