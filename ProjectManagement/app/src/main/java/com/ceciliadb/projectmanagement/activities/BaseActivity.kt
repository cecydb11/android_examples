package com.ceciliadb.projectmanagement.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.ceciliadb.projectmanagement.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.dialog_progress.*

open class BaseActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false
    private lateinit var mProgressDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun showProgressDialog(text: String){
        mProgressDialog = Dialog(this)
        mProgressDialog.setContentView(R.layout.dialog_progress)
        mProgressDialog.tv_progress_text.text = text
        mProgressDialog.show()
    }

    fun hideProgressDialog(){
        mProgressDialog.dismiss()
    }

    fun getCurrentUserID(): String{
        return FirebaseAuth.getInstance().currentUser!!.uid
    }

    fun doubleBackToExit(){
        //If the user presses back twice it will close the app.
        if(doubleBackToExitPressedOnce){
            super.onBackPressed()
            return
        }
        //The variable is true because it was pressed once so it asks the user to press again
        // (so this will be called again and the variable will be true).
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, resources.getString(R.string.click_back_again_exit), Toast.LENGTH_SHORT).show()

        //A delay so if the user doesn't press the back button again within 2 seconds the variable will be false again
        Handler().postDelayed({
            doubleBackToExitPressedOnce = false
        }, 2000)
    }

    fun showErrorSnackBar(message: String){
        //Prepare the snackbar with the received error text.
        val snackBar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        //Edit the view of the snackbar ie. the color.
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(ContextCompat.getColor(this, R.color.snackbar_error_color))
        snackBar.show()
    }

    fun showSuccessSnackBar(message: String){
        //Prepare the snackbar with the received error text.
        val snackBar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        //Edit the view of the snackbar ie. the color.
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(ContextCompat.getColor(this, R.color.snackbar_success_color))
        snackBar.show()
    }

}
