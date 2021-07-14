package com.example.travelapp.ui.options.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.travelapp.MainActivity
import com.example.travelapp.databinding.FragmentOptionMenuBinding
import com.example.travelapp.ui.options.base.OptionsViewModelFactory
import com.example.travelapp.ui.options.main.viewmodels.OptionsViewModel


class OptionMenuFragment : Fragment() {

    private lateinit var binding: FragmentOptionMenuBinding

    private lateinit var viewModel : OptionsViewModel

    private lateinit var arguments: OptionMenuFragmentArgs


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getNavArguments()
        configureBinding(inflater)
        configViewModel()
        setActionBar()




        return binding.root
    }

    private fun configViewModel() {
        val modelFactory = OptionsViewModelFactory()
        viewModel = ViewModelProvider(this, modelFactory).get(OptionsViewModel::class.java)
    }

    private fun configureBinding(inflater: LayoutInflater) {
        binding = FragmentOptionMenuBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.country = arguments.country!!

        binding.covid.setOnClickListener {
            findNavController().navigate(OptionMenuFragmentDirections.actionOptionMenuFragmentToCovidHistoryTodayFragment(arguments.country!!))
            viewModel.onWheaterInfoNavigated()
        }

        binding.weather.setOnClickListener {
            findNavController().navigate(OptionMenuFragmentDirections.actionOptionMenuFragmentToCitiesPopupFragment(arguments.country!!))
            viewModel.onCovidInfoNavigated()
        }

    }

    private fun getNavArguments() {
        arguments = OptionMenuFragmentArgs.fromBundle(requireArguments())
    }

    private fun setActionBar() {
        val country = arguments.country!!
        val name = if(country.countryNameNl.isNotBlank()) country.countryNameNl else country.name
        (activity as MainActivity?)?.setActionBarTitle("Info over $name")
    }

}