package com.example.walmartexercise.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmartexercise.model.CountryNetwork
import com.example.walmartexercise.model.UIState
import com.example.walmartexercise.model.api.CountryRepository
import com.example.walmartexercise.model.data.CountryResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class CountryViewModel(
    private val repository: CountryRepository,
    private val dispatcher: CoroutineDispatcher
): ViewModel() {
    private val _countries = MutableLiveData<UIState>()
    val countries: LiveData<UIState>
        get() = _countries

    private val coroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e("CountryViewModel",
                "Context: $coroutineContext\nMessage: ${throwable.localizedMessage}",
                throwable)
        }
    }

    private val viewModelSafeScope by lazy {
        viewModelScope + coroutineExceptionHandler
    }

    fun getCountries(){
        viewModelSafeScope.launch(dispatcher) {
            repository.getCountries().collect{
                _countries.postValue(it)
            }
        }
    }

    fun setLoading() { _countries.value = UIState.Loading }
}