package com.example.countries.di

import com.example.countries.Model.CountriesService
import com.example.countries.ViewModel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: CountriesService)
    fun inject(viewModel: ListViewModel)

}