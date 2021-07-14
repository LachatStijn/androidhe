package com.example.travelapp.ui.covid.main.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.travelapp.MainActivity
import com.example.travelapp.data.api.ApiHelper
import com.example.travelapp.data.api.RetrofitBuilder
import com.example.travelapp.data.model.Timeline
import com.example.travelapp.databinding.FragmentCovidHistoryTodayBinding
import com.example.travelapp.ui.covid.base.CovidViewModelFactory
import com.example.travelapp.ui.covid.main.viewmodel.CovidViewModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*


class CovidHistoryTodayFragment : Fragment() {

    private lateinit var binding: FragmentCovidHistoryTodayBinding

    companion object {
        var last5 : Long = 5L
    }

    private lateinit var viewModel : CovidViewModel

    private lateinit var arguments : CovidHistoryTodayFragmentArgs


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getNavArguments()
        configBinding(inflater)
        configViewModel()
        getData(arguments.country!!.alpha2Code.lowercase())
        setActionBar()

        binding
        return binding.root
    }

    private fun getData(country : String) {
        viewModel.getData(country)

        viewModel.covidInfo.observe(this.viewLifecycleOwner, Observer { response ->
            response?.let {
                binding.totalNumberOfCases.text = response.cases.toString()
                binding.newCases.text = response.todayCases.toString()
                binding.totalNumberOfDeaths.text = response.deaths.toString()
                binding.newDeaths.text = response.todayDeaths.toString()
                binding.criticalCases.text = response.critical.toString()
            }
        })

        viewModel.historicalData.observe(this.viewLifecycleOwner, Observer { response ->
            response?.let {
                createHistoricalCasesGraph(response.timeline)
                createHistoricalDeathsGraph(response.timeline)
            }
        })
    }

    private fun createHistoricalDeathsGraph(timeline: Timeline) {

        var json : String = timeline.deaths.toString().replace('{', ' ').replace('}',' ').replace('=',':')

        var listDeaths = listOf<Float>()

        var gson : Gson = Gson()
        json = gson.toJson(json)
        var test = json.split(",")

        for (variable in test){
            var new = variable.trim().split(":").get(1).trim().replace('"', ' ').trim()
            listDeaths = listDeaths.plus(new.toFloat())
        }


        var currentDate = Calendar.getInstance()
        currentDate.add(Calendar.DAY_OF_WEEK, -1)


        var monthAgo  = Calendar.getInstance()
        monthAgo.time = currentDate.time
        monthAgo.add(Calendar.MONTH, -1)
        monthAgo.add(Calendar.DAY_OF_WEEK, 1)





        var listDate = listOf<Float>()
        while(monthAgo.before(currentDate)){
            listDate = listDate.plus(monthAgo.timeInMillis.toFloat())

            last5 = monthAgo.timeInMillis.toString().substring(monthAgo.timeInMillis.toString().length-5).toString().toLong()
            monthAgo.add(Calendar.DAY_OF_WEEK, 1)

        }

        listDate = listDate.plus(currentDate.timeInMillis.toFloat())

        var entries = listOf<Entry>()

        for (i in 0..listDate.size-1){
            entries = entries.plus(Entry(listDate[i], listDeaths[i]))
        }

        var lds = LineDataSet(entries, "Deaths")
        lds.color = Color.RED
        var ld = LineData(lds)

        binding.dodenAantal.xAxis.valueFormatter = XAxisValueFormatter()
        binding.dodenAantal.data = ld
        binding.dodenAantal.data.notifyDataChanged()
        binding.dodenAantal.invalidate()
    }

    private fun createHistoricalCasesGraph(timeline: Timeline) {

        var json : String = timeline.cases.toString().replace('{', ' ').replace('}',' ').replace('=',':')

        var listCases = listOf<Float>()

        var gson : Gson = Gson()
        json = gson.toJson(json)
        var test = json.split(",")

        for (variable in test){
            var new = variable.trim().split(":").get(1).trim().replace('"', ' ').trim()
            listCases = listCases.plus(new.toFloat())
        }

        var currentDate = Calendar.getInstance()
        currentDate.add(Calendar.DAY_OF_WEEK, -1)


        var monthAgo  = Calendar.getInstance()
        monthAgo.time = currentDate.time
        monthAgo.add(Calendar.MONTH, -1)
        monthAgo.add(Calendar.DAY_OF_WEEK, 1)





        var listDate = listOf<Float>()
        while(monthAgo.before(currentDate)){
            listDate = listDate.plus(monthAgo.timeInMillis.toFloat())

            last5 = monthAgo.timeInMillis.toString().substring(monthAgo.timeInMillis.toString().length-5).toString().toLong()
            monthAgo.add(Calendar.DAY_OF_WEEK, 1)

        }

        listDate = listDate.plus(currentDate.timeInMillis.toFloat())

        var entries = listOf<Entry>()

        for (i in 0..listDate.size-1){
            entries = entries.plus(Entry(listDate[i], listCases[i]))
        }

        var lds = LineDataSet(entries, "Cases")
        lds.color = Color. BLUE
        var ld = LineData(lds)

        binding.besmetAantal.xAxis.valueFormatter = XAxisValueFormatter()
        binding.besmetAantal.data = ld
        binding.besmetAantal.data.notifyDataChanged()
        binding.besmetAantal.invalidate()

    }

    private fun setActionBar() {
        val country = arguments.country!!
        val name = if(country.countryNameNl.isNotBlank()) country.countryNameNl else country.name
        (activity as MainActivity?)?.setActionBarTitle("Covid in $name")
    }

    private fun configViewModel() {
        val modelFactory = CovidViewModelFactory(ApiHelper(RetrofitBuilder.apiServiceCovid))
        viewModel = ViewModelProvider(this, modelFactory).get(CovidViewModel::class.java)
    }

    private fun configBinding(inflater: LayoutInflater) {
        binding = FragmentCovidHistoryTodayBinding.inflate(inflater)
        binding.lifecycleOwner = this
    }

    private fun getNavArguments() {
        arguments = CovidHistoryTodayFragmentArgs.fromBundle(requireArguments())

    }
}

class XAxisValueFormatter : ValueFormatter() {

    private var mFormat: SimpleDateFormat = SimpleDateFormat("dd/MM")

    override fun getFormattedValue(value: Float): String {



        var test2 = value.toLong() / 100000
        var test3  = test2 * 100000
        test3 += CovidHistoryTodayFragment.last5


        var date : Date = Date(test3)
        return mFormat.format(date)

    }
}
