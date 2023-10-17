package com.example.walmartexercise.view

import androidx.fragment.app.Fragment
import com.example.walmartexercise.model.CountryNetwork

open class ViewModelFragment: Fragment() {

    protected val viewModel by lazy {
        CountryNetwork.provideViewModel(requireActivity())
    }
}