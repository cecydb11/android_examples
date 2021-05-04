package com.ceciliadb.projectmanagement.activities

import android.os.Bundle
import android.view.WindowManager
import com.ceciliadb.projectmanagement.R
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setupActionBar()
    }

    private fun setupActionBar(){
        //Prepare the action toolbar with the icon we have.
        setSupportActionBar(toolbar_sign_in_activity)
        val actionBar = supportActionBar
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
        }

        //Sets the toolbar in the sign up activity (back icon) to act as if you press the back button.
        toolbar_sign_in_activity.setNavigationOnClickListener {onBackPressed()}
    }
}
