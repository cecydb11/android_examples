package com.example.countries.Model

import io.reactivex.Single
import retrofit2.http.GET

interface CountriesAPI {
    @GET("DevTides/countries/master/countriesV2.json")
    //Single means it's an observable that emits one variable and then closes.
    fun getCountries():Single<List<Country>>
}