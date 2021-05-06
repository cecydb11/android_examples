package com.ceciliadb.projectmanagement.firebase

import com.ceciliadb.projectmanagement.activities.SignUpActivity
import com.ceciliadb.projectmanagement.models.User
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreClass {

    //We create an instance of the firestore
    private val mFireStore = FirebaseFirestore.getInstance()

    fun registerUser(activity: SignUpActivity, userInfo: User)
}