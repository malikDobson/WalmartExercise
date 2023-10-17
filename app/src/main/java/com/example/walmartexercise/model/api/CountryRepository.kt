package com.example.walmartexercise.model.api


import android.util.Log
import com.example.walmartexercise.model.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface CountryRepository {
    suspend fun getCountries(): Flow<UIState>
}

class CountryRepositoryImpl(private val api: CountryApi): CountryRepository {
    override suspend fun getCountries(): Flow<UIState> =
        flow {
            try {
                val response = api.getAllCountries()
                if(response.isSuccessful) {
                    emit(response.body()?.let {
                        UIState.Success(it)
                    } ?: throw Exception("Empty Response"))
                } else throw Exception("Failed Network Call")
            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }

}