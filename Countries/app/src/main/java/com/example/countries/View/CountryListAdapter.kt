package com.example.countries.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.Model.Country
import com.example.countries.R
import com.example.countries.util.getProgressDrawable
import com.example.countries.util.loadImage
import kotlinx.android.synthetic.main.item_country.view.*

class CountryListAdapter(var countries: ArrayList<Country>):RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    fun updateCountries(newCountries: List<Country>){
        //We clean and re-fill the list of countries and notify the data has
        // changed in order to refresh the view.
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CountryViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_country, parent, false))

    override fun getItemCount()= countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    class CountryViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val countryName = view.name
        private val countryCapital = view.capital
        private val countryFlagg = view.iv_flag
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(country: Country){
            countryName.text = country.countryName
            countryCapital.text = country.capital
            countryFlagg.loadImage(country.flag, progressDrawable)

        }
    }
}