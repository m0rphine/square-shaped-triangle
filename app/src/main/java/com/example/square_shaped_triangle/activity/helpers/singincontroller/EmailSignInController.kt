package com.example.square_shaped_triangle.activity.helpers.singincontroller

import android.app.Activity
import android.util.Log
import com.example.square_shaped_triangle.activity.helpers.ValidationHelper
import com.google.firebase.auth.FirebaseAuth

class EmailSignInController(var eventCallback: EventCallback, val context: Activity) {
    //Firebase references
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun loginUser(email: String, password: String) {
        if (ValidationHelper.validateEmailAndPassword(email, password)) {
            Log.d(TAG, "Logging in user.")
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(context) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithEmail:success")
                        //val uid = mAuth.currentUser?.uid

                        eventCallback.updateUI()
                    } else {
                        Log.e(TAG, "signInWithEmail:failure", task.exception)
                        eventCallback.onError("Authentication failed.")
                    }
                }
        } else {
            eventCallback.onError("Enter all details")
        }
    }

    fun createNewAccount(email: String, password: String) {
        if (ValidationHelper.validateEmailAndPassword(email, password)) {
            createUserWithEmailAndPassword(email, password)
        } else {
            eventCallback.onError("Enter all details")
        }
    }


    private fun createUserWithEmailAndPassword(email: String, password: String) {

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(context) { task ->
                if (task.isSuccessful) { // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    verifyEmail() //Verify Email
                    eventCallback.updateUI()
                } else { // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    eventCallback.onError("Authentication failed.")
                }
            }
    }

    private fun verifyEmail() {
        val mUser = mAuth.currentUser;
        mUser?.sendEmailVerification()?.addOnCompleteListener(context) { task ->
            if (task.isSuccessful) {
                eventCallback.onError("Verification email sent to " + mUser.getEmail())
            } else {
                Log.e(TAG, "sendEmailVerification", task.exception)
                eventCallback.onError("Failed to send verification email.")
            }
        }
    }

    companion object {
        private val TAG = "EmailSignInController"
    }
}

