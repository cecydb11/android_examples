package com.ceciliadb.projectmanagement.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.ceciliadb.projectmanagement.R
import com.ceciliadb.projectmanagement.firebase.FirestoreClass
import com.ceciliadb.projectmanagement.models.User
import com.ceciliadb.projectmanagement.utils.Constants
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_my_profile.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.io.IOException

class MyProfileActivity : BaseActivity() {

    companion object{
        private const val READ_STORAGE_PERMISSION_CODE = 1
        private const val PICK_IMAGE_REQUEST_CODE = 1
    }

    private var mSelectedImageFileUri: Uri? = null
    private var mProfileImageURI: String = ""
    private lateinit var mUserDetails: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        setupActionBar()

        FirestoreClass().loadUserData(this)

        iv_user_image.setOnClickListener {
            //We verify if the permission is granted otherwise we request it.
            if(ContextCompat.checkSelfPermission(
                    this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED){
                showImageChooser()
            }else{
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), READ_STORAGE_PERMISSION_CODE)
            }
        }

        btn_update.setOnClickListener {
            if(mSelectedImageFileUri != null){
                uploadUserImage()
            }else{
                showProgressDialog(resources.getString(R.string.please_wait))
                updateUserProfileData()
            }
        }
    }



    //On response we verify if we have the permission.
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == READ_STORAGE_PERMISSION_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                showImageChooser()
            }else{
                showErrorSnackBar("You denied storage permissions. " +
                        "You can allow it from the settings.")
            }
        }
    }

    private fun showImageChooser(){
        //We open an intent to pick the image
        var galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //We save the result of the image chooser activity to the Uri for the image.
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE_REQUEST_CODE
            && data!!.data != null){
            mSelectedImageFileUri = data.data

            try{
                //We use a third part library to set the image from Firebase to the ImageView
                Glide
                    .with(this)
                    .load(mSelectedImageFileUri)
                    .centerCrop()
                    .placeholder(R.drawable.ic_user_place_holder)
                    .into(iv_user_image)

            }catch (e: IOException){
                e.printStackTrace()
            }

        }
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
        mUserDetails = user
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

    private fun updateUserProfileData(){
        //We validate if there were any changes and we update the hashmap with the new values.
        val userHashMap = HashMap<String, Any>()
        var anyChangesMade = false
        if (mProfileImageURI.isNotEmpty() && mProfileImageURI != mUserDetails.image){
            userHashMap[Constants.IMAGE] = mProfileImageURI
            anyChangesMade = true
        }
        if(et_profile_name.text.toString() != mUserDetails.name){
            userHashMap[Constants.NAME] = et_profile_name.text.toString()
            anyChangesMade = true
        }
        if(et_profile_mobile.text.toString() != mUserDetails.mobile.toString()){
            userHashMap[Constants.MOBILE] = et_profile_mobile.text.toString().toLong()
            anyChangesMade = true
        }

        //If there were any changes we update the data.
        if(anyChangesMade){
            FirestoreClass().updateUserProfileData(this, userHashMap)
            hideProgressDialog()
        }
    }

    private fun uploadUserImage(){
        showProgressDialog(resources.getString(R.string.please_wait))
        /*We store the image using the path we create, and then validate
        if it was stored successfully, if so, we get the download link to
        store it in a variable mProfileImageURI and then in the database too.*/
        if(mSelectedImageFileUri != null) {
            val sRef: StorageReference = FirebaseStorage.getInstance().reference.child(
                "USER_IMAGE" + System.currentTimeMillis()
                        + "." + getFileExtension(mSelectedImageFileUri)
            )
            //We put the file (from the URI) to our Firebase Storage instance
            sRef.putFile(mSelectedImageFileUri!!).addOnSuccessListener { taskSnapshot ->
                Log.e(
                    "Firebase Image URL",
                    taskSnapshot.metadata!!.reference!!.downloadUrl.toString()
                )

                taskSnapshot.metadata!!.reference!!.downloadUrl.addOnSuccessListener { uri ->
                    Log.i("Downloadable Image URL", uri.toString())
                    mProfileImageURI = uri.toString()

                    updateUserProfileData()
                }
            }.addOnFailureListener{
                exception ->
                showErrorSnackBar(exception.message!!)
                hideProgressDialog()
            }
        }
    }

    private fun getFileExtension(uri:Uri?):String?{
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(contentResolver.getType(uri!!))
    }

    fun profileUpdateSuccess(){
        hideProgressDialog()
        finish()
    }
}
