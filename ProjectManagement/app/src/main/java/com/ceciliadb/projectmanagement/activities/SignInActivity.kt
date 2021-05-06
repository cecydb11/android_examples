package com.ceciliadb.projectmanagement.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import com.ceciliadb.projectmanagement.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : BaseActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setupActionBar()


    }

    public override fun onStart() {
        super.onStart()
        auth = FirebaseAuth.getInstance()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            showErrorSnackBar("User already logged in")
            //reload();

        }
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

        btn_sign_in.setOnClickListener {
            signIn()
        }
    }

    private fun signIn(){
        //Get the values from the edit texts
        val email: String = et_mail.text.toString().trim{it <= ' '}
        val password: String = et_password.text.toString().trim{it <= ' '}

        //Calls the validation function and send the values we got,
        //it does something depending on the response.
        if(validateForm(email, password)){
            //We send a predetermined string to the progress dialog we created in the base activity
            showProgressDialog(resources.getString(R.string.please_wait))

            //We use our firebase connection to sign in with email and password
            //as we previously enabled in the Firebase console
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    //If it was completed we close the progress dialog
                    hideProgressDialog()
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        showSuccessSnackBar("Login successful.")
                        val user = auth.currentUser
                        startActivity(
                                Intent(this, MainActivity::class.java)
                        )
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        showErrorSnackBar("Authentication failed.")
                        //updateUI(null)
                    }
                }
        }
    }

    private fun validateForm(email: String, password: String): Boolean{
        //Validates each value and it will not return true unless all values are not empty,
        //else, it will show the error snackbar.
        return when{
            TextUtils.isEmpty(email)->{
                showErrorSnackBar("Please enter your email.")
                false
            }
            TextUtils.isEmpty(password)->{
                showErrorSnackBar("Please enter your password.")
                false
            }else ->{
                true
            }
        }
    }
}
