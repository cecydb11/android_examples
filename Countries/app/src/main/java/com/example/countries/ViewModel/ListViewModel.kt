package com.example.countries.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countries.Model.CountriesService
import com.example.countries.Model.Country
import com.example.countries.di.DaggerApiComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel: ViewModel() {

    @Inject
    lateinit var countriesService: CountriesService
    init{
        DaggerApiComponent.create().inject(this)
    }
    //This objects gets the response and then cleans after itself
    private val disposable = CompositeDisposable()

    //Anyone who subscribes to countries will be notified when it gets updates.
    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        //We call the private function from here so we won't expose it to the outside.
        fetchCountries()
    }

    private fun fetchCountries(){
        loading.value = true
        //Helps to handle the calls in a background thread
        disposable.add(countriesService.getCountries()
            //Subscribes to the variable in a new thread.
            .subscribeOn(Schedulers.newThread())
            //The result should come back to the main thread (what the user is seeing).
            .observeOn(AndroidSchedulers.mainThread())
            //It defines the functionality of what we will do once we get a response.
            .subscribeWith(object: DisposableSingleObserver<List<Country>>(){
                override fun onSuccess(value: List<Country>?) {
                    countries.value = value
                    countryLoadError.value = false
                    loading.value = false
                }

                override fun onError(e: Throwable?) {
                    countryLoadError.value = true
                    loading.value = false
                }

            })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}