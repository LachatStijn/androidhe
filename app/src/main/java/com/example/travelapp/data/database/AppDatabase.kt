package com.example.travelapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.travelapp.data.api.ApiHelper
import com.example.travelapp.data.api.RetrofitBuilder
import com.example.travelapp.data.dao.CityDao
import com.example.travelapp.data.dao.CountryDao
import com.example.travelapp.data.dao.CovidDao
import com.example.travelapp.data.dao.WeatherDao
import com.example.travelapp.data.entity.city.CityEntity
import com.example.travelapp.data.entity.country.CountryEntity
import com.example.travelapp.data.entity.covid.CovidCasesEntity
import com.example.travelapp.data.entity.covid.CovidDeathsEntity
import com.example.travelapp.data.entity.covid.CovidInfoEntity
import com.example.travelapp.data.entity.covidHistorical.CovidHistoricalEntity
import com.example.travelapp.data.entity.forecast.ForecastEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


@Database(entities = [CountryEntity::class, CityEntity::class, CovidCasesEntity::class, CovidDeathsEntity:: class, CovidHistoricalEntity::class, CovidInfoEntity::class, ForecastEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun countryDao() : CountryDao
    abstract fun cityDao() : CityDao
    abstract fun covidDao() : CovidDao
    abstract fun weatherDao() : WeatherDao

        companion object {
        @Volatile private var instance : AppDatabase? = null

        fun getInstance(context : Context): AppDatabase {
            return instance ?: synchronized(this){
                instance ?: buildDatabase(context).also {instance = it}
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "TravelDatabase").addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    val viewModelJob = Job()
                    CoroutineScope(Dispatchers.IO + viewModelJob).launch {
                        val countriesApi = ApiHelper(RetrofitBuilder.apiServiceCountries)
                        val citiesApi = ApiHelper(RetrofitBuilder.apiServiceCities)

                        val countryDao = getInstance(context).countryDao()
                        val cityDao = getInstance(context).cityDao()

                        countriesApi.getCountries().forEach {
                            var c = CountryEntity(0, it.name, it.alpha2Code, it.capital, it.region, it.population.toFloat(), it.flag, if(it.translations.nl == null) "" else it.translations.nl)
                            var result = countryDao.insertOne(c)

                            citiesApi.getCitiesByCountry(it.name).forEach { city ->
                                var city = CityEntity(city.id, city.name, result.toInt())
                                cityDao.insertOne(city)
                            }
                        }
                    }
                }
            }).build()
        }
    }



}
