package com.example.countries.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countries.R
import com.example.countries.ViewModel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        countriesList.apply {
            layoutManager  = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }
        observeViewModel()
    }

    fun observeViewModel(){
        //The observer behaves depending on the results from the variables.
        viewModel.countries.observe(this, Observer { countries ->
            //If countries is not null, the adapter will update the countries with "it" (countries).
            countriesList.visibility = View.VISIBLE
            countries?.let { countriesAdapter.updateCountries(it) }
        })

        viewModel.countryLoadError.observe(this, Observer { isError ->
            //If isError is not empty, it will show or hide the error message.
            isError?.let { list_error.visibility = if(it) View.VISIBLE else View.GONE}

        })

        viewModel.loading.observe(this, Observer { isLoading ->
            //If isLoading is not empty it, it will show or hide the loading view.
            isLoading?.let {
                loading_view.visibility = if(it) View.VISIBLE else View.GONE
                //If "it" (isLoading == true), we hide everything else.
                if(it){
                    list_error.visibility = View.GONE
                    countriesList.visibility = View.GONE
                }

            }
        })
    }

}
