package com.example.countries.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.countries.R

fun getProgressDrawable(context: Context): CircularProgressDrawable{
    //It creates a small spinner  to display while the image loads.
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10F
        centerRadius = 50F
        start()
    }
}

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable){
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.ic_error_black_24dp)
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)


}