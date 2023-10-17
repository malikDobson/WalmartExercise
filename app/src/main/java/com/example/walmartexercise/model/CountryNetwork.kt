package com.example.walmartexercise.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.walmartexercise.model.api.CountryApi
import com.example.walmartexercise.model.api.CountryRepositoryImpl
import com.example.walmartexercise.viewmodel.CountryViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object CountryNetwork {

    private fun initRetrofit(): CountryApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(CountryApi::class.java)
    }

    private fun provideRepository() = CountryRepositoryImpl(initRetrofit())
    private fun provideDispatcher() = Dispatchers.IO

    private fun createClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    fun provideViewModel(storeOwner: ViewModelStoreOwner): CountryViewModel {
        return ViewModelProvider(storeOwner, object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CountryViewModel(provideRepository(), provideDispatcher()) as T
            }
        })[CountryViewModel::class.java]
    }
}