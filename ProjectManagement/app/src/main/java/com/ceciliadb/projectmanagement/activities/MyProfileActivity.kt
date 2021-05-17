package com.ceciliadb.projectmanagement.activities

import android.os.Bundle
import com.bumptech.glide.Glide
import com.ceciliadb.projectmanagement.R
import com.ceciliadb.projectmanagement.firebase.FirestoreClass
import com.ceciliadb.projectmanagement.models.User
import kotlinx.android.synthetic.main.activity_my_profile.*

class MyProfileActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        setupActionBar()

        FirestoreClass().loadUserData(this)
    }

    private fun setupActionBar(){
        setSupportActionBar(toolbar_my_profile_activity)
        val actionBar = supportActionBar
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
            actionBar.title = resources.getString(R.string.my_profile)
        }

        toolbar_my_profile_activity.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    fun setUserDataInUI(user: User){
        //We use a third part library to set the image from Firebase to the ImageView
        Glide
            .with(this)
            .load(user.image)
            .centerCrop()
            .placeholder(R.drawable.ic_user_place_holder)
            .into(iv_user_image)

        //We set the profile data from Firebase to the TextView
        et_profile_name.setText(user.name)
        et_profile_email.setText(user.email)
        //The default value from the document is 0, so if it's 0 we don't show it.
        if(user.mobile != 0L){
            et_profile_mobile.setText(user.mobile.toString())
        }
    }
}
