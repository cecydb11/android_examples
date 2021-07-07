package com.example.countries.Model

import com.example.countries.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class CountriesService {

    @Inject
    lateinit var api: CountriesAPI
    init{
        //Dagger + Name of our component interface.
        DaggerApiComponent.create().inject(this)

    }

    fun getCountries(): Single<List<Country>>{
        return api.getCountries()
    }
}