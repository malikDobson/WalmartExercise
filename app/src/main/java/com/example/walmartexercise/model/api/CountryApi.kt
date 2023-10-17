package com.example.walmartexercise.model.api

import com.example.walmartexercise.model.ENDPOINT
import com.example.walmartexercise.model.data.CountryResponse
import retrofit2.Response
import retrofit2.http.GET

interface CountryApi {
    @GET(ENDPOINT)
    suspend fun getAllCountries(): Response<List<CountryResponse>>
}