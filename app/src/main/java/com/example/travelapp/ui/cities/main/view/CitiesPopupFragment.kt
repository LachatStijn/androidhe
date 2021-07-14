package com.example.travelapp.ui.cities.main.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelapp.MainActivity
import com.example.travelapp.data.api.ApiHelper
import com.example.travelapp.data.api.RetrofitBuilder
import com.example.travelapp.data.database.AppDatabase
import com.example.travelapp.databinding.FragmentCitiesPopupBinding
import com.example.travelapp.ui.cities.base.CitiesViewModelFactory
import com.example.travelapp.ui.cities.main.adapter.CitiesAdapter
import com.example.travelapp.ui.cities.main.viewmodel.CitiesViewModel


class CitiesPopupFragment : Fragment() {

    private lateinit var binding: FragmentCitiesPopupBinding

    private lateinit var viewModel : CitiesViewModel

    private lateinit var adapterObj : CitiesAdapter

    private lateinit var arguments : CitiesPopupFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getNavArguments()
        setActionBar()
        configBinding(inflater)
        configViewModel()
        getCities()
        filteringData()
        observingData()

        adapterObj.notifyDataSetChanged()
        return binding.root
    }

    private fun filteringData() {
        binding.filter.doOnTextChanged { text, start, before, count ->
            viewModel.filterData(text)
        }
    }

    private fun getCities() {
        //viewModel.getCitiesByCountry(arguments.country)

    }

    private fun observingData() {
        viewModel.getCitiesByCountry(arguments.country.countryId).observe(this.viewLifecycleOwner, Observer { list ->
            list?.let {
                Log.i("cities", list.toString())
                adapterObj.submitList(list)
            }
        })

        viewModel.navigateToWeatherDetails.observe(this.viewLifecycleOwner, Observer{city ->
            city?.let {
                findNavController().navigate(CitiesPopupFragmentDirections.actionCitiesPopupFragmentToWeatherFragment(city, arguments.country))
                viewModel.onCityWeatherDetailsNavigated()
                binding.filter.text.clear()
            }
        })
    }


    private fun configViewModel() {
        val dataSource = AppDatabase.getInstance(this.requireContext())
        val modelFactory = CitiesViewModelFactory(ApiHelper(RetrofitBuilder.apiServiceCities), dataSource)
        viewModel = ViewModelProvider(this, modelFactory).get(CitiesViewModel::class.java)
    }

    private fun configBinding(inflater: LayoutInflater) {
        binding = FragmentCitiesPopupBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val adap = CitiesAdapter(CitiesAdapter.CityClickListener{ city ->
            viewModel.onCityClicked(city)
        })

        binding.cities.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapterObj = adap
            adapter = adap
        }
    }

    private fun getNavArguments() {
        arguments = CitiesPopupFragmentArgs.fromBundle(requireArguments())

    }

    private fun setActionBar() {
        val country = arguments.country!!
        val name = if(country.countryNameNl.isNotBlank()) country.countryNameNl else country.name
        (activity as MainActivity?)?.setActionBarTitle("Steden in $name")
    }



}