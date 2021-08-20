package com.example.travelapp.ui.weather.main.view


import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelapp.MainActivity
import com.example.travelapp.R
import com.example.travelapp.data.entity.forecast.ForecastEntity
import com.example.travelapp.data.model.WeatherForecast
import com.example.travelapp.databinding.CardWeatherDetailsBinding
import com.example.travelapp.databinding.FragmentWeatherBinding
import com.example.travelapp.databinding.LinechartWeatherBinding
import com.example.travelapp.ui.weather.main.adapter.WeatherAdapter
import com.example.travelapp.ui.weather.main.viewmodel.WeatherViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat


class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding

    private lateinit var detailsBinding : CardWeatherDetailsBinding

    private lateinit var lineChartBinding : LinechartWeatherBinding

    private var entries = listOf<Entry>()

    private var entriesXAxisLong = listOf<Long>()

    private val viewModel: WeatherViewModel by inject()

    private lateinit var adapterObj: WeatherAdapter


    private lateinit var arguments: WeatherFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getNavArguments()
        (activity as MainActivity?)?.setActionBarTitle("${arguments.city.name}, ${arguments.country.name}")
        configBinding(inflater)
        getLocalForecasts()
        setHasOptionsMenu(true)
        observingData()


        return binding.root
    }

    private fun getLocalForecasts() {
        viewModel.setCountry(arguments.city)
    }



    private fun observingData() {

        viewModel.localForecasts.observe(this.viewLifecycleOwner, Observer {
            setActionBar(it)
            var q = "${arguments.city.name},${arguments.country.iso_2.lowercase()}"
            var units = "metric"
            if(it.isEmpty()) {
                viewModel.getWeatherForecast(q, units)
            }else{
                viewModel.handleDatabaseCall(it)
            }
        })

        viewModel.weatherForecasts.observe(this.viewLifecycleOwner, Observer { weatherForecasts ->
            adapterObj.submitList(weatherForecasts)
            configureLineChart(weatherForecasts)
        })

        viewModel.showDetailsOfForecast.observe(this.viewLifecycleOwner, Observer { wf ->
            wf?.let {
                createDetails(wf, binding.forecasts.id)
                showChart()
            }
        })

        viewModel.cardView.observe(this.viewLifecycleOwner, Observer {
        })




    }

    private fun configureLineChart(weatherForecasts: List<WeatherForecast>) {
        var metrics = Resources.getSystem().displayMetrics
        var dp : Int = (metrics.density * 6f).toInt()

        var ll = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT )
        ll.setMargins(dp)


        lineChartBinding.theCard.cardElevation = (metrics.density * 8f)
        lineChartBinding.theCard.layoutParams = ll



        var valueFormatter = XAxisValueFormatterHours()
        valueFormatter.setDays(weatherForecasts.map {
            it.dt_txt.substring(11,16)
        })




        entries = listOf()
        entries = weatherForecasts.mapIndexed { index, wf ->
            Entry(index.toFloat(), wf.main.temp.toFloat())
        }


        var lds = LineDataSet(entries, "Weervoorspellingen voor komende 5 dagen")
        lds.color = Color.BLUE
        var ld = LineData(lds)
        ld.setValueTextSize(11f)


        lineChartBinding.lineChartGF.xAxis.valueFormatter = valueFormatter
        lineChartBinding.lineChartGF.xAxis.position = XAxis.XAxisPosition.BOTTOM

        lineChartBinding.lineChartGF.axisRight.isEnabled = false
        lineChartBinding.lineChartGF.legend.textSize = 14f
        lineChartBinding.lineChartGF.data = ld
        lineChartBinding.lineChartGF.data.notifyDataChanged()
        lineChartBinding.lineChartGF.invalidate()

        if(binding.forecastConstraint.findViewById<CardView>(lineChartBinding.theCard.id) == null){
            binding.forecastConstraint.addView(lineChartBinding.theCard)
            lineChartBinding.lineChartGF.zoom(5f,0f,5f,0f)
        }


        lineChartBinding.theCard.visibility = View.INVISIBLE

    }

    private fun showChart() {
        lineChartBinding.theCard.visibility = View.VISIBLE
    }

    private fun createDetails(wf : WeatherForecast, id: Int) {

        var index = 1;



        detailsBinding.wf = wf

        var metrics = Resources.getSystem().displayMetrics
        var dp : Int = (metrics.density * 6f).toInt()

        var cvExists : CardView? = binding.forecastConstraint.findViewById(detailsBinding.cardDetails.id)
        if(cvExists !== null){
            binding.forecastConstraint.removeView(cvExists)
        }

        var lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        lp.leftMargin = dp
        lp.rightMargin = dp


        binding.forecastConstraint.addView(detailsBinding.cardDetails, index,lp)


    }

    private fun setActionBar(list : List<ForecastEntity>) {
        var text : String
        val format = SimpleDateFormat("HH:mm")

        if(list.isEmpty()) {
            text = "${arguments.city.name}, ${arguments.country.name}"
        } else {

            var sb : StringBuilder = java.lang.StringBuilder(format.format(list[0].updated))
            var int = format.format(list[0].updated).substring(0,2).toInt()+2
            sb.delete(0,2)
            if(int < 10){
                sb.insert(0, "0${int}")
            }else{
                sb.insert(0,int.toString())
            }

            text = "${arguments.city.name}, ${arguments.country.name} : $sb"
        }

        (activity as MainActivity?)?.setActionBarTitle(text)
    }

    private fun configBinding(inflater: LayoutInflater) {
        binding = FragmentWeatherBinding.inflate(inflater)
        lineChartBinding = LinechartWeatherBinding.inflate(inflater)

        var inflater : LayoutInflater = this.requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        detailsBinding = CardWeatherDetailsBinding.inflate(inflater)

        binding.lifecycleOwner = this


        val adap = WeatherAdapter(WeatherAdapter.WeatherClickListener { wf, cv ->
            viewModel.onForecastClicked(wf, cv)
        })

        binding.forecasts.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            adapterObj = adap
            adapter = adap
        }


    }

    private fun getNavArguments() {
        arguments = WeatherFragmentArgs.fromBundle(requireArguments())

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.weather_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.refresh){
            viewModel.removeByCity()

            var cvExists : CardView? = binding.forecastConstraint.findViewById(detailsBinding.cardDetails.id)
            if(cvExists !== null){
                binding.forecastConstraint.removeView(cvExists)
            }

            lineChartBinding.theCard.visibility = View.INVISIBLE
        }

        return super.onOptionsItemSelected(item)
    }
}


class XAxisValueFormatterHours : ValueFormatter() {

    private lateinit var hours : List<String>

    override fun getFormattedValue(value: Float): String {
        return hours[value.toInt()]
    }

    fun setDays(hours : List<String>){
        this.hours = hours
    }
}
