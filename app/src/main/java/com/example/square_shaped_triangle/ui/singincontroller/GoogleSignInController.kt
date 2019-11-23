package com.example.square_shaped_triangle.activity.helpers.singincontroller

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.activity.helpers.UserSharedPreferenceHelper.Companion.newInstance
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class GoogleSignInController(var eventCallback: EventCallback, val context: Context) {
    //Firebase references
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    //Google SignIn elements
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mGoogleSignInOptions: GoogleSignInOptions

    fun configureGoogleSignIn() {
        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(context, mGoogleSignInOptions)
    }

    fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        eventCallback.startActivityForResult(signInIntent)
    }

    fun handleGoogleSignIn(data: Intent?) {
        Log.i(TAG, " handleGoogleSignIn")
        val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)
            account?.let {
                newInstance(context).userId = it.id
                newInstance(context).name = it.displayName
                newInstance(context).uri = it.photoUrl.toString()
                Log.i(TAG + "id", it.id.toString())
                firebaseAuthWithGoogle(it) }
        } catch (e: ApiException) {
            eventCallback.onError("Google sign in failed. 1")
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        //val personId = acct.id
        //GoogleSignInAccount parameters !!!!!!!!!!!Need to add in DB !!!!!!!!!!!
        /*val personName = acct.displayName
        val personGivenName = acct.givenName
        val personFamilyName = acct.familyName
        val personEmail = acct.email
        val personId = acct.id
        val personPhoto: Uri? = acct.photoUrl
        Log.d(LoginActivity.TAG, personName.orEmpty())
        Log.d(LoginActivity.TAG, personGivenName.orEmpty())
        Log.d(LoginActivity.TAG, personFamilyName.orEmpty())
        Log.d(LoginActivity.TAG, personEmail.orEmpty())
        Log.d(LoginActivity.TAG, personId.orEmpty())
        Log.d(LoginActivity.TAG, personPhoto.toString())*/

        mAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.i(TAG, "isSuccessful")
                eventCallback.updateUI()
            } else {
                eventCallback.onError("Google sign in failed. 2")
            }
        }
    }

    companion object {
        private val TAG = "GoogleSignIn"
    }
}