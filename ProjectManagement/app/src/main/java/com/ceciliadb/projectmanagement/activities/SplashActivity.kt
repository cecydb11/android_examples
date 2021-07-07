package com.ceciliadb.projectmanagement.activities

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.ceciliadb.projectmanagement.R
import com.ceciliadb.projectmanagement.firebase.FirestoreClass
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val typeFace : Typeface = Typeface.createFromAsset(assets, "Neuton-Bold.ttf")
        tv_splash_title.typeface = typeFace

        Handler().postDelayed({
            /*We get the current user ID which could be an ID if there's a user already logged in
            or an empty string in which case we will open the intro activity to either sign in or
            register a new user.*/
            var currentUserID = FirestoreClass().getCurrentUserID()
            if(currentUserID.isNotEmpty()){
                startActivity(Intent(this, MainActivity::class.java))
            }else {
                startActivity(Intent(this, IntroActivity::class.java))
            }
            finish()
        }, 2500)

    }
}
