package com.example.travelapp.ui.weather.main.view


import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelapp.MainActivity
import com.example.travelapp.data.api.ApiHelper
import com.example.travelapp.data.api.RetrofitBuilder
import com.example.travelapp.data.model.WeatherForecast
import com.example.travelapp.databinding.CardWeatherDetailsBinding
import com.example.travelapp.databinding.FragmentWeatherBinding
import com.example.travelapp.databinding.LinechartWeatherBinding
import com.example.travelapp.ui.weather.base.WeatherViewModelFactory
import com.example.travelapp.ui.weather.main.adapter.WeatherAdapter
import com.example.travelapp.ui.weather.main.viewmodel.WeatherViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat
import java.util.*


class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding

    private lateinit var detailsBinding : CardWeatherDetailsBinding

    private lateinit var lineChartBinding : LinechartWeatherBinding

    private var entries = listOf<Entry>()

    private var entriesXAxisLong = listOf<Long>()

    private lateinit var viewModel: WeatherViewModel

    private lateinit var adapterObj: WeatherAdapter


    private lateinit var arguments: WeatherFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getNavArguments()
        setActionBar()
        configBinding(inflater)
        configViewModel()
        getWeatherForecasts()
        observingData()


        return binding.root
    }

    private fun getWeatherForecasts() {

        var q = "${arguments.city.name}, ${arguments.country.alpha2Code.lowercase()}"
        var units = "metric"
        viewModel.getWeatherForecast(q, units)

    }



    private fun observingData() {


        viewModel.showDetailsOfForecast.observe(this.viewLifecycleOwner, Observer { wf ->
            wf?.let {
                createDetails(wf, binding.forecasts.id)
                if(binding.forecastConstraint.findViewById<CardView>(lineChartBinding.theCard.id) === null){
                    createLineChart()
                }

            }
        })

        viewModel.weatherForecasts.observe(this.viewLifecycleOwner, Observer { obj ->
            obj?.let {
                adapterObj.submitList(obj.list)

            }
        })


    }

    private fun createLineChart() {

        var metrics = Resources.getSystem().displayMetrics
        var dp : Int = (metrics.density * 6f).toInt()

        var ll = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT )
        ll.setMargins(dp)

        lineChartBinding.theCard.layoutParams = ll


        binding.forecastConstraint.addView(lineChartBinding.theCard)

        for (wf : WeatherForecast in viewModel.weatherForecasts.value!!.list) {
            entries = entries.plus(Entry(wf.dt, wf.main.temp.toFloat()))
        }


        var lds = LineDataSet(entries, "Forecasts")
        lds.color = Color.BLUE
        var ld = LineData(lds)

        lineChartBinding.lineChartGF.zoom(4f,0f,4f,0f)
        lineChartBinding.lineChartGF.fitScreen()


        lineChartBinding.lineChartGF.xAxis.valueFormatter = XAxisValueFormatter()
        lineChartBinding.lineChartGF.xAxis.position = XAxis.XAxisPosition.TOP
        lineChartBinding.lineChartGF.xAxis.resetAxisMinimum()

        lineChartBinding.lineChartGF.data = ld
        lineChartBinding.lineChartGF.data.notifyDataChanged()
        lineChartBinding.lineChartGF.invalidate()


    }

    private fun createDetails(wf : WeatherForecast, id: Int) {

        var index = 1;

        var inflater : LayoutInflater = this.requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        detailsBinding = CardWeatherDetailsBinding.inflate(inflater)

        detailsBinding.wf = wf

        var metrics = Resources.getSystem().displayMetrics
        var dp : Int = (metrics.density * 6f).toInt()

        var cvExists : CardView? = binding.forecastConstraint.findViewById(detailsBinding.cardDetails.id)
        if(cvExists !== null){
            binding.forecastConstraint.removeView(cvExists)
        }

        var lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        lp.setMargins(dp)

        binding.forecastConstraint.addView(detailsBinding.cardDetails, index,lp)


    }

    private fun setActionBar() {
        (activity as MainActivity?)?.setActionBarTitle("${arguments.city.name}, ${arguments.country.countryNameNl}")
    }

    private fun configViewModel() {
        val modelFactory = WeatherViewModelFactory(ApiHelper(RetrofitBuilder.apiServiceWeather))
        viewModel = ViewModelProvider(this, modelFactory).get(WeatherViewModel::class.java)
    }

    private fun configBinding(inflater: LayoutInflater) {
        binding = FragmentWeatherBinding.inflate(inflater)
        lineChartBinding = LinechartWeatherBinding.inflate(inflater)

        binding.lifecycleOwner = this


        val adap = WeatherAdapter(WeatherAdapter.WeatherClickListener { wf ->
            viewModel.onForecastClicked(wf)
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
}

class XAxisValueFormatter : ValueFormatter() {

    private var mFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")

    override fun getFormattedValue(value: Float): String {
        var date : Date = Date(value.toLong())
        return mFormat.format(date).subSequence(11,16).toString()

    }
}
