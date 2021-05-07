package com.ceciliadb.projectmanagement.firebase

import android.util.Log
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

    fun signInUser(activity: SignInActivity){
        mFireStore.collection(Constants.USERS)
            .document(getCurretUserID())
            .get()
            .addOnSuccessListener {document ->
                //We convert the document we got into an object of type User in order to use
                //the information from the firestore database
                val loggedInUser = document.toObject(User::class.java)

                //Once the user is logged in successfully we continue with the sign in activity.
                activity.loginSuccess(loggedInUser)
            }.addOnFailureListener{
                    e->
                Log.e(activity.javaClass.simpleName, "Error while registering the user: $e")
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