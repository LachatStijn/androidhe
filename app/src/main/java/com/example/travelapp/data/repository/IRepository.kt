package com.example.travelapp.data.repository

interface IRepository<out A> {
    suspend fun getAll(): A
}