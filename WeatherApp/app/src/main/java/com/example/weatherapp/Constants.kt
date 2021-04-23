package com.example.weatherapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object Constants{
    const val APP_ID: String = "9764cf801f8b9f264e5fad7de02a13d2"
    const val BASE_URL: String = "https://api.openweathermap.org/data/"
    const val METRIC_UNIT : String = "metric"

    fun isNetworkAvailable(context: Context) : Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        //We need to validate the SDK version, for newer versions we use this code.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetword = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when{
                activeNetword.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetword.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetword.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }else{
            //For older versions we use the deprecated code.
            val networdInfo = connectivityManager.activeNetworkInfo
            return networdInfo != null && networdInfo.isConnectedOrConnecting
        }
    }


}