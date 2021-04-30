package com.example.projectmanagement.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.projectmanagement.R
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setupActionBar()
    }

    private fun setupActionBar(){
        setSupportActionBar(toolbar_sign_in_activity)
        val actionBar = supportActionBar
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
        }

        toolbar_sign_in_activity.setNavigationOnClickListener {onBackPressed()}
    }
}
