package com.example.travelapp.ui.covid.main.viewmodel

import androidx.lifecycle.*
import com.example.travelapp.data.entity.country.CountryEntity
import com.example.travelapp.data.entity.covid.CovidInfoEntity
import com.example.travelapp.data.entity.covidHistorical.CovidCasesEntity
import com.example.travelapp.data.entity.covidHistorical.CovidDeathsEntity
import com.example.travelapp.data.entity.covidHistorical.CovidHistoricalDeathsCases
import com.example.travelapp.data.entity.covidHistorical.CovidHistoricalEntity
import com.example.travelapp.data.model.TimelineObject
import com.example.travelapp.data.repository.CovidRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CovidViewModel(private var covidRepo : CovidRepo) : ViewModel() {

    private val format = SimpleDateFormat("dd/MM")

    private var _remoteCovidInfo = MutableLiveData<CovidInfoEntity>()
    val remoteCovidInfo: LiveData<CovidInfoEntity>
        get() = _remoteCovidInfo

    private var _remoteCasesAndDeaths = MutableLiveData<CovidHistoricalDeathsCases>()
    val remoteCasesAndDeathsCases : LiveData<CovidHistoricalDeathsCases>
        get() = _remoteCasesAndDeaths


    //if countryEntity changes LocalCovidInfo & LocalHistoricalInfo are called
    private val countryEntity = MutableLiveData<CountryEntity>()
    fun setCountry(country : CountryEntity){
        countryEntity.value = country
    }

    val localCovidInfo: LiveData<CovidInfoEntity> = Transformations.switchMap(countryEntity) {
            countryEntity -> covidRepo.getCovidInfoFromCountry(countryEntity.countryId)
    }

    val localHistoricalInfo: LiveData<CovidHistoricalEntity> = Transformations.switchMap(countryEntity){
            countryEntity -> covidRepo.getHistoricalInfoFromCountry(countryEntity.countryId)
    }

    //if historicalEntity changes LocalCasesAndDeaths are called
    private val historicalEntity = MutableLiveData<CovidHistoricalEntity>()
    fun setHistorical(historical: CovidHistoricalEntity){
        historicalEntity.value = historical
    }

    val localCasesAndDeaths : LiveData<CovidHistoricalDeathsCases> = Transformations.switchMap(historicalEntity) {
        historicalEntity -> covidRepo.getCasesAndDeaths(historicalEntity.covidHistoricalId)
    }




    fun getRemoteCovidInfo(country : CountryEntity){
        viewModelScope.launch {
            var result = covidRepo.getNewCovidInformation("countries/"+country.name)
            var covidInfoEntity = CovidInfoEntity(
                0,
                result.updated,
                result.countryInfo.iso2,
                result.cases.toFloat(),
                result.todayCases.toFloat(),
                result.deaths.toFloat(),
                result.todayDeaths.toFloat(),
                result.recovered.toFloat(),
                result.todayRecovered.toFloat(),
                result.critical.toFloat(),
                result.tests.toFloat(),
                country.countryId,
                Calendar.getInstance().time
            )
            insertCovidInfo(covidInfoEntity)
            //_remoteCovidInfo.value = covidInfoEntity
        }
    }

    private suspend fun insertCovidInfo(covidInfoEntity: CovidInfoEntity) {
        var job = CoroutineScope(Dispatchers.IO).launch {
            covidRepo.insertOneCovidInfo(covidInfoEntity)
        }
        job.join()
    }

    fun getRemoteHistoricalInfo(country : CountryEntity){
        viewModelScope.launch {

            var result = covidRepo.getHistoricalInfo("historical/"+country.name)
            var historicalInfo = CovidHistoricalEntity(
                0,
                country.countryId,
            )

            var historicalEntity = covidRepo.insertOneHistoricalInfo(historicalInfo)

            var cases = mapCases(result.timeline.cases,historicalEntity);
            var deaths = mapDeaths(result.timeline.deaths, historicalEntity)

            //_remoteCasesAndDeaths.value = CovidHistoricalDeathsCases(historicalInfo, deaths, cases)

            insertCasesAndDeaths(cases, deaths)


        }
    }

    private suspend fun insertCasesAndDeaths(cases: List<CovidCasesEntity>, deaths: List<CovidDeathsEntity>) {
        var job = CoroutineScope(Dispatchers.IO).launch {
            covidRepo.insertAllCovidCases(cases)
            covidRepo.insertAllDeaths(deaths)
        }
        job.join()
    }

    private fun mapDeaths(deaths: Any?, historicalEntity: Long): List<CovidDeathsEntity> {
        var calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_WEEK, -1)

        var convertedDeaths = deaths.toString().replace("{", "").replace("}", "").replace("=", ":")
        var toListDeaths = convertedDeaths.split(",")

        var deathsTimelineObjects = toListDeaths.map {
            TimelineObject(it.split(":")[0].trim(), it.split(":")[1].toFloat())
        }

        return deathsTimelineObjects.mapIndexed { index, any ->
            if(index != 0){
                calendar.add(Calendar.DAY_OF_WEEK, -1)
            }

            CovidDeathsEntity(
                0,
                format.format(calendar.time),
                deathsTimelineObjects[index].amount,
                historicalEntity
            )

        }
    }

    private fun mapCases(cases: Any?, historicalEntity: Long): List<CovidCasesEntity> {

        var calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_WEEK, -1)

        var convertedCases = cases.toString().replace("{", "").replace("}", "").replace("=", ":")
        var toListCases = convertedCases.split(",")

        var casesTimelineObjects = toListCases.map {
            TimelineObject(it.split(":")[0].trim(), it.split(":")[1].toFloat())
        }

        return casesTimelineObjects.mapIndexed { index, any ->
            if(index != 0){
                calendar.add(Calendar.DAY_OF_WEEK, -1)
            }

            CovidCasesEntity(
                0,
                format.format(calendar.time),
                casesTimelineObjects[index].amount,
                historicalEntity
            )

        }
    }

    fun refresh(){
        viewModelScope.launch {
            var job = viewModelScope.launch(Dispatchers.IO){
                covidRepo.removeCovidInfo(countryEntity.value!!.countryId)
                covidRepo.removeHistoricalCascade(countryEntity.value!!.countryId)
            }
            job.join()
        }
    }


}