package com.ceciliadb.projectmanagement.activities

import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import com.ceciliadb.projectmanagement.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setupActionBar()
    }

    private fun setupActionBar(){
        //Prepare the action toolbar with the icon we have.
        setSupportActionBar(toolbar_sign_up_activity)
        val actionBar = supportActionBar
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
        }

        //Sets the toolbar in the sign up activity (back icon) to act as if you press the back button.
        toolbar_sign_up_activity.setNavigationOnClickListener {onBackPressed()}

        //Call the register user function when the user clicks on the "Sign up" button
        btn_sign_up.setOnClickListener {
            registerUser()
        }

    }

    private fun registerUser(){
        //Get the values from the edit texts
        val name: String = et_name.text.toString().trim{it <= ' '}
        val email: String = et_email.text.toString().trim{it <= ' '}
        val password: String = et_password.text.toString().trim{it <= ' '}

        //Calls the validation function and send the values we got,
        //it does something depending on the response.
        if(validateForm(name, email, password)){
            Toast.makeText(this, "Registration ready.", Toast.LENGTH_LONG).show()
        }
    }

    private fun validateForm(name: String, email: String, password: String): Boolean{
        //Validates each value and it will not return true unless all 3 values are not empty,
        //else, it will show the error snackbar.
        return when{
            TextUtils.isEmpty(name)->{
                showErrorSnackBar("Please enter your name.")
                false
            }TextUtils.isEmpty(email)->{
                showErrorSnackBar("Please enter your email.")
                false
            }TextUtils.isEmpty(password)->{
                showErrorSnackBar("Please enter your password.")
                false
            }else ->{
                true
            }
        }
    }
}
