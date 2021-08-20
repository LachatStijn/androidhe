package com.example.travelapp.ui.cities.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelapp.MainActivity
import com.example.travelapp.databinding.FragmentCitiesPopupBinding
import com.example.travelapp.ui.cities.main.adapter.CitiesAdapter
import com.example.travelapp.ui.cities.main.viewmodel.CitiesViewModel
import org.koin.android.ext.android.inject


class CitiesPopupFragment : Fragment() {

    private lateinit var binding: FragmentCitiesPopupBinding

    private val viewModel : CitiesViewModel by inject()

    private lateinit var adapterObj : CitiesAdapter

    private lateinit var arguments : CitiesPopupFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        getNavArguments()
        setActionBar()
        configBinding(inflater)
        getCities()
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
        viewModel.getCitiesByCountry(arguments.country)
    }

    private fun observingData() {

        viewModel.countryCitiesEntity.observe(this.viewLifecycleOwner, Observer { it ->
            it?.let {
                viewModel.actualDataSet.value = it
            }
        })

        viewModel.actualDataSet.observe(this.viewLifecycleOwner, Observer{ lijst ->
            lijst?.let {
                adapterObj.submitList(lijst)
                filteringData()
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

    private fun configBinding(inflater: LayoutInflater) {
        binding = FragmentCitiesPopupBinding.inflate(inflater)
        binding.viewModel = viewModel
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
        (activity as MainActivity?)?.setActionBarTitle("Steden in ${country.name}")
    }



}