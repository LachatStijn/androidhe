package com.example.travelapp

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.travelapp.data.dao.CityDao
import com.example.travelapp.data.dao.CountryDao
import com.example.travelapp.data.dao.CovidDao
import com.example.travelapp.data.dao.WeatherDao
import com.example.travelapp.data.database.AppDatabase
import com.example.travelapp.data.entity.city.CityEntity
import com.example.travelapp.data.entity.forecast.ForecastEntity
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TravelAppDatabaseTest {
    private lateinit var cityDao : CityDao
    private lateinit var countryDao: CountryDao
    private lateinit var covidDao: CovidDao
    private lateinit var weatherDao: WeatherDao
    private lateinit var db : AppDatabase

    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        cityDao = db.cityDao()
        countryDao = db.countryDao()
        covidDao = db.covidDao()
        weatherDao = db.weatherDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetCity(){
        CoroutineScope(Dispatchers.Default).launch {
            val city = CityEntity(0, "test", 1L)
            var result = CoroutineScope(Dispatchers.IO).launch{
                cityDao.insertAll(listOf(city))
            }
            result.join()
            val cityTest = cityDao.getCityByName(city.name)
            assertEquals(cityTest?.city?.name, "test")
            assertEquals(cityTest?.city?.countryFk, 1L)
            assertEquals(cityTest?.forecasts, listOf<ForecastEntity>())
        }
    }






}