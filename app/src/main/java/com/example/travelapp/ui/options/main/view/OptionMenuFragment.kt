package com.example.travelapp.ui.options.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.travelapp.MainActivity
import com.example.travelapp.databinding.FragmentOptionMenuBinding
import com.example.travelapp.ui.options.main.viewmodels.OptionMenuViewModel
import org.koin.android.ext.android.inject


class OptionMenuFragment : Fragment() {

    private lateinit var binding: FragmentOptionMenuBinding

    private lateinit var arguments: OptionMenuFragmentArgs

    private val viewModel : OptionMenuViewModel by inject()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getNavArguments()
        setActionBar()
        configureBinding(inflater)

        return binding.root
    }

    private fun configureBinding(inflater: LayoutInflater) {
        binding = FragmentOptionMenuBinding.inflate(inflater)
        binding.lifecycleOwner = this

        viewModel.getAlpha2Code(arguments.country)


        binding.country = arguments.country

        binding.covid.setOnClickListener{
            findNavController().navigate(OptionMenuFragmentDirections.actionOptionMenuFragmentToCovidHistoryTodayFragment(viewModel.newCountry))

        }

        binding.weather.setOnClickListener {
            findNavController().navigate(OptionMenuFragmentDirections.actionOptionMenuFragmentToCitiesPopupFragment(viewModel.newCountry))
        }

    }

    private fun getNavArguments() {
        arguments = OptionMenuFragmentArgs.fromBundle(requireArguments())
    }

    private fun setActionBar() {
        val country = arguments.country!!
        val name = if(country.name.isNotBlank()) country.name else country.name
        if(requireActivity() is MainActivity){
            (activity as MainActivity).setActionBarTitle("Info over $name")
        }
    }

}