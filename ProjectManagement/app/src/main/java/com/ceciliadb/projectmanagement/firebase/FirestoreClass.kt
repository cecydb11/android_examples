package com.ceciliadb.projectmanagement.firebase

import android.app.Activity
import android.util.Log
import com.ceciliadb.projectmanagement.activities.MainActivity
import com.ceciliadb.projectmanagement.activities.SignInActivity
import com.ceciliadb.projectmanagement.activities.SignUpActivity
import com.ceciliadb.projectmanagement.models.User
import com.ceciliadb.projectmanagement.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FirestoreClass {

    //We create an instance of the firestore
    private val mFireStore = FirebaseFirestore.getInstance()

    fun registerUser(activity: SignUpActivity, userInfo: User){
        //We create a document for each user inside the users collection
        mFireStore.collection(Constants.USERS)
            .document(getCurretUserID())
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                //Once the user is registered successfully we continue with the sign up activity.
                activity.userRegisteredSuccess()
            }.addOnFailureListener{
                e->
                Log.e(activity.javaClass.simpleName, "Error while registering the user: $e")
            }
    }

    fun signInUser(activity: Activity){
        mFireStore.collection(Constants.USERS)
            .document(getCurretUserID())
            .get()
            .addOnSuccessListener {document ->
                //We convert the document we got into an object of type User in order to use
                //the information from the firestore database
                val loggedInUser = document.toObject(User::class.java)!!

                when(activity){
                    is SignInActivity->{
                        //Once the user is logged in successfully we continue with the sign in activity.
                        activity.loginSuccess(loggedInUser)
                    }
                    is MainActivity->{
                        //From the main activity we set the logged in user image and name.
                        activity.updateNavigationUserDetails(loggedInUser)
                    }
                }

            }.addOnFailureListener{ e->
                when(activity){
                    //We need to write the same code because else the code won't know what type of
                    // activity we are talking about or if the activity has that method.
                    is SignInActivity->{
                        activity.hideProgressDialog()
                    }
                    is MainActivity->{
                        activity.hideProgressDialog()
                    }
                }
                Log.e(activity.javaClass.simpleName, "Error while logging in: $e")
            }

    }

    fun getCurretUserID(): String{
        var currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if(currentUser != null){
            currentUserID = currentUser.uid
        }
        return currentUserID
    }
}