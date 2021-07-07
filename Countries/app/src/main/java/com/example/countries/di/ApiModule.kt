package com.example.countries.di

import com.example.countries.Model.CountriesAPI
import com.example.countries.Model.CountriesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class ApiModule {
    private val BASE_URL = "https://raw.githubusercontent.com/"
    @Provides
    fun provideCountriesApi(): CountriesAPI{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            //check what is used instead of rxjava
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountriesAPI::class.java)
    }
    @Provides
    fun provideCountriesService():CountriesService{
        return CountriesService()


    }
}