package com.example.travelapp.ui.overview.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.travelapp.MainActivity
import com.example.travelapp.data.api.ApiHelper
import com.example.travelapp.data.api.RetrofitBuilder
import com.example.travelapp.data.database.AppDatabase
import com.example.travelapp.databinding.FragmentOverzichtBinding
import com.example.travelapp.ui.overview.base.CountryViewModelFactory
import com.example.travelapp.ui.overview.main.adapter.CountriesAdapter
import com.example.travelapp.ui.overview.main.viewmodels.CountriesViewModel


class OverzichtFragment : Fragment() {

    private lateinit var binding : FragmentOverzichtBinding

    private lateinit var viewModel: CountriesViewModel

    private lateinit var  adapterObj: CountriesAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        configBinding(inflater)
        configViewModel()
        setActionBar()

        //observing Data
        viewModel.databaseCountries.observe(this.viewLifecycleOwner, Observer { list ->
            list?.let {
                adapterObj.submitList(list)
            }
        })



        viewModel.navigateToShowOptions.observe(this.viewLifecycleOwner, Observer { country ->
            country?.let {
                findNavController().navigate(OverzichtFragmentDirections.actionOverzichtFragmentToOptionMenuFragment(country))
                viewModel.onCountryOptionsNavigated()
            }
        })

        adapterObj.notifyDataSetChanged()
        return binding.root
    }



    private fun configViewModel() {
        val dataSource = AppDatabase.getInstance(this.requireContext())
        val modelFactory = CountryViewModelFactory(dataSource,ApiHelper(RetrofitBuilder.apiServiceCountries))
        viewModel = ViewModelProvider(this, modelFactory).get(CountriesViewModel::class.java)
    }

    private fun configBinding(inflater: LayoutInflater){
        binding = FragmentOverzichtBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val adap = CountriesAdapter(CountriesAdapter.ClickListener{ country ->
            viewModel.onCountryClicked(country)
        })

        binding.countries.apply {
            layoutManager = GridLayoutManager(this.context ,2)
            adapterObj = adap
            adapter = adap
        }


    }

    private fun setActionBar() {
        (activity as MainActivity?)?.setActionBarTitle("Selecteer bestemming")
    }
}

