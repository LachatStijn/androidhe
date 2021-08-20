package com.example.travelapp.ui.overview.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.travelapp.MainActivity
import com.example.travelapp.databinding.FragmentOverzichtBinding
import com.example.travelapp.ui.overview.main.adapter.CountriesAdapter
import com.example.travelapp.ui.overview.main.viewmodels.CountriesViewModel
import org.koin.android.ext.android.inject


class OverzichtFragment : Fragment() {

    private lateinit var binding : FragmentOverzichtBinding

    private val viewModel: CountriesViewModel by inject()

    private lateinit var  adapterObj: CountriesAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        configBinding(inflater)
        observeData()


        adapterObj.notifyDataSetChanged()
        return binding.root
    }

    private fun observeData() {

        viewModel.localCountries.observe(this.viewLifecycleOwner, Observer { list ->
            list?.let {
                if(list.isEmpty()) viewModel.getCountries() else { adapterObj.submitList(list) }
            }
        })


        viewModel.navigateToShowOptions.observe(this.viewLifecycleOwner, Observer { country ->
            country?.let {
                findNavController().navigate(OverzichtFragmentDirections.actionOverzichtFragmentToOptionMenuFragment(country))
                viewModel.onCountryOptionsNavigated()
            }
        })
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

    override fun onStart() {
        super.onStart()
        setActionBar()
    }
}

