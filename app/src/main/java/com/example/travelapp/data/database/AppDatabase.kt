 package com.example.travelapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.travelapp.data.Converters.DateConverter
import com.example.travelapp.data.dao.CityDao
import com.example.travelapp.data.dao.CountryDao
import com.example.travelapp.data.dao.CovidDao
import com.example.travelapp.data.dao.WeatherDao
import com.example.travelapp.data.entity.city.CityEntity
import com.example.travelapp.data.entity.country.CountryEntity
import com.example.travelapp.data.entity.covid.CovidInfoEntity
import com.example.travelapp.data.entity.covidHistorical.CovidCasesEntity
import com.example.travelapp.data.entity.covidHistorical.CovidDeathsEntity
import com.example.travelapp.data.entity.covidHistorical.CovidHistoricalEntity
import com.example.travelapp.data.entity.forecast.ForecastEntity


@Database(entities = [CountryEntity::class, CityEntity::class, CovidCasesEntity::class, CovidDeathsEntity:: class, CovidHistoricalEntity::class, CovidInfoEntity::class, ForecastEntity::class], version = 13, exportSchema = false)@TypeConverters(DateConverter::class)
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
                return Room.databaseBuilder(context, AppDatabase::class.java, "TravelDatabase").fallbackToDestructiveMigration().addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                }).build()
            }
        }
}
