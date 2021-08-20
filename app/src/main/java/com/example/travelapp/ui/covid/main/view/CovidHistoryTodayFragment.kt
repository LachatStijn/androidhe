package com.example.travelapp.ui.covid.main.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.travelapp.MainActivity
import com.example.travelapp.R
import com.example.travelapp.data.entity.covid.CovidInfoEntity
import com.example.travelapp.data.entity.covidHistorical.CovidHistoricalDeathsCases
import com.example.travelapp.databinding.FragmentCovidHistoryTodayBinding
import com.example.travelapp.ui.covid.main.viewmodel.CovidViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.LargeValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*


class CovidHistoryTodayFragment : Fragment() {

    private val format = SimpleDateFormat("dd/MM")

    private lateinit var binding: FragmentCovidHistoryTodayBinding

    private val valueFormatter = XAxisValueFormatter()

    private val viewModel : CovidViewModel by inject()

    private lateinit var arguments : CovidHistoryTodayFragmentArgs



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getNavArguments()
        setHasOptionsMenu(true)
        (activity as MainActivity?)?.setActionBarTitle("Covid, ${arguments.country.name}")
        configBinding(inflater)

        getLocalData()
        observingData()


        binding
        return binding.root
    }

    private fun getLocalData(){
        viewModel.setCountry(arguments.country)
    }

    private fun observingData(){



        viewModel.localCovidInfo.observe(this.viewLifecycleOwner, Observer{
            if(it == null){
                setActionBar(null)
                viewModel.getRemoteCovidInfo(arguments.country)
            }else{
                Log.i("date", it.update.toString())
                setActionBar(it)
                setupDetails(
                    it.casesAmount.toString(),
                    it.todayCasesAmount.toString(),
                    it.deathsAmount.toString(),
                    it.todayDeathsAmount.toString(),
                    it.critical.toString())
            }
        })

        viewModel.localHistoricalInfo.observe(this.viewLifecycleOwner, Observer {
            if(it == null){
                viewModel.getRemoteHistoricalInfo(arguments.country)
            }else{
                viewModel.setHistorical(it)
            }
        })

        viewModel.localCasesAndDeaths.observe(this.viewLifecycleOwner, Observer{
            if(it !== null){
                if(it.deaths.size !== 0){
                    setGraph(it)
                }
            }
        })


    }

    private fun setGraph(it: CovidHistoricalDeathsCases) {



        var deaths = it.deaths.mapIndexed { index, death ->
            Entry(index.toFloat(), death.number)
        }


        var cases = it.cases.mapIndexed{ index, case  ->
            Entry(index.toFloat(), case.number)
        }

        var deathsLds = LineDataSet(deaths, "Doden COVID-19")

        deathsLds.color = Color.RED

        var casesLds = LineDataSet(cases, "Besmettingen COVID-19")
        casesLds.color = Color.BLUE

        var largeValueFormatter = LargeValueFormatter()
        largeValueFormatter.setMaxLength(8)

        var lineDataDeaths = LineData(deathsLds)
        lineDataDeaths.setValueFormatter(largeValueFormatter)
        lineDataDeaths.setValueTextSize(11f)

        var lineDataCases = LineData(casesLds)
        lineDataCases.setValueFormatter(largeValueFormatter)
        lineDataCases.setValueTextSize(11f)

        val valueFormatter = setDaysValueFormatter(it.deaths.map { it.date })

        //graph deaths


        binding.dodenAantal.axisRight.isEnabled = false
        binding.dodenAantal.axisLeft.valueFormatter = largeValueFormatter
        binding.dodenAantal.xAxis.valueFormatter = valueFormatter
        binding.dodenAantal.xAxis.position = XAxis.XAxisPosition.BOTTOM
        binding.dodenAantal.data = lineDataDeaths
        binding.dodenAantal.fitScreen()
        binding.dodenAantal.zoom(5f,0f,5f,0f)

        binding.dodenAantal.data.notifyDataChanged()
        binding.dodenAantal.invalidate()



        //graph cases
        binding.casesAantalGraph.axisRight.isEnabled = false
        binding.casesAantalGraph.axisLeft.valueFormatter = largeValueFormatter
        binding.casesAantalGraph.xAxis.valueFormatter = valueFormatter
        binding.casesAantalGraph.xAxis.position = XAxis.XAxisPosition.BOTTOM
        binding.casesAantalGraph.data = lineDataCases


        binding.casesAantalGraph.fitScreen()
        binding.casesAantalGraph.zoom(5f,0f,5f,0f)

        binding.casesAantalGraph.data.notifyDataChanged()
        binding.casesAantalGraph.invalidate()

    }


    private fun setupDetails(cases:String, casesToday : String, deaths:String, deathsToday : String, critical : String) {
        binding.totalNumberOfCases.text = "Totaal aantal besmettingen: ${cases}"
        binding.newCases.text = "Besmettingen vandaag: ${casesToday}"
        binding.totalNumberOfDeaths.text = "Totaal aantal doden: ${deaths}"
        binding.newDeaths.text = "Doden vandaag: ${deathsToday}"
        binding.criticalCases.text = "Totaal aantal op intesieve zorgen: ${critical}"
    }

    private fun setDaysValueFormatter(cities: List<String>) : XAxisValueFormatter{
        Collections.reverse(cities)
        valueFormatter.setDays(cities)
        return valueFormatter
    }



    private fun setActionBar(covidInfo : CovidInfoEntity?) {

        val format = SimpleDateFormat("HH:mm")
        var text : String
        if(covidInfo == null) {
            text = "Covid, ${arguments.country.name}"
        } else {

            var sb : StringBuilder = java.lang.StringBuilder(format.format(covidInfo.updated))
            Log.i("strinbuilder", sb.toString())
            var int = format.format(covidInfo.updated).substring(0,2).toInt()+2
            Log.i("aftersubstring", int.toString())
            sb.delete(0,2)
            if(int < 10){
                sb.insert(0, "0${int}")
            }else{
                sb.insert(0,int.toString())
            }

            text = "Covid, ${arguments.country.name} : $sb"
        }

        (activity as MainActivity?)?.setActionBarTitle(text)
    }

    private fun configBinding(inflater: LayoutInflater) {
        binding = FragmentCovidHistoryTodayBinding.inflate(inflater)
        binding.lifecycleOwner = this
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.weather_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.refresh){
            viewModel.refresh()
            //removeByCity()

            /*var cvExists : CardView? = binding.forecastConstraint.findViewById(detailsBinding.cardDetails.id)
            if(cvExists !== null){
                binding.forecastConstraint.removeView(cvExists)
            }

            lineChartBinding.theCard.visibility = View.INVISIBLE*/
        }

        return super.onOptionsItemSelected(item)
    }

    private fun getNavArguments() {
        arguments = CovidHistoryTodayFragmentArgs.fromBundle(requireArguments())
    }

    class XAxisValueFormatter : ValueFormatter() {

        private lateinit var days : List<String>

        override fun getFormattedValue(value: Float): String {
            return days[value.toInt()]
        }

        fun setDays(days : List<String>){
            this.days = days
        }
    }
}


