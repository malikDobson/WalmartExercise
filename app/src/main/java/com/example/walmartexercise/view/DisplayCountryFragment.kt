package com.example.walmartexercise.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.walmartexercise.databinding.FragmentCountryListBinding
import com.example.walmartexercise.model.UIState
import com.example.walmartexercise.model.data.CountryResponse
import com.example.walmartexercise.view.adapter.CountryAdapter

class DisplayCountryFragment: ViewModelFragment() {
    private lateinit var binding: FragmentCountryListBinding
    private val countryAdapter by lazy {
        CountryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryListBinding.inflate(layoutInflater)
        configureObserver()
        viewModel.getCountries()
        return binding.root
    }

    private fun configureObserver() {
        viewModel.countries.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UIState.Loading -> {
                    binding.loadingInfo.visibility = View.VISIBLE
                    binding.loadingText.visibility = View.VISIBLE
                }
                is UIState.Error -> {
                    binding.loadingInfo.visibility = View.GONE
                    binding.loadingText.text = uiState.error.message
                }
                is UIState.Success<*> -> {
                    binding.apply {
                        loadingInfo.visibility = View.GONE
                        loadingText.visibility = View.GONE
                        countryAdapter.setCountryList((uiState.response as List<CountryResponse>))
                        browseCountryRecycler.adapter = countryAdapter
                    }
                }
            }
        }
    }
}