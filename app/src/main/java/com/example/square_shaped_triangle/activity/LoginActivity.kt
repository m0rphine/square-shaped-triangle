package com.example.square_shaped_triangle.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.data.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class LoginActivity : AppCompatActivity() { // need Refactor
    private lateinit var email: String
    private lateinit var password: String

    //UI elements
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnCreateAccount: Button
    private lateinit var google_button: SignInButton

    //Firebase references
    private lateinit var mAuth: FirebaseAuth

    //Google SignIn elements
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mGoogleSignInOptions: GoogleSignInOptions


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initialise()
        configureGoogleSignIn()
    }

    private fun initialise() {
        etEmail = findViewById<View>(R.id.login_input_email) as EditText
        etPassword = findViewById<View>(R.id.login_input_password) as EditText
        btnLogin = findViewById<View>(R.id.button_login) as Button
        btnCreateAccount = findViewById<View>(R.id.create_account_button) as Button
        google_button = findViewById<View>(R.id.google_button) as SignInButton
        mAuth = FirebaseAuth.getInstance()

        btnCreateAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnLogin.setOnClickListener { loginUser() }
        google_button.setOnClickListener { signIn() }
    }

    private fun loginUser() {
        email = etEmail.text.toString()
        password = etPassword.text.toString()

        if (validateFillParameters()) {
            Log.d(TAG, "Logging in user.")
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithEmail:success")
                        val user = User(
                            mAuth.currentUser?.uid.orEmpty(),
                            email,
                            password
                        )
                        updateUI(user)
                    } else {
                        Log.e(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            this, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } else {
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
        }
    }

    private fun configureGoogleSignIn() {
        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                account?.let { firebaseAuthWithGoogle(it) }
            } catch (e: ApiException) {
                Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)

        //GoogleSignInAccount parameters
        val personName = acct.displayName
        val personGivenName = acct.givenName
        val personFamilyName = acct.familyName
        val personEmail = acct.email
        val personId = acct.id
        val personPhoto: Uri? = acct.photoUrl
        Log.d(TAG, personName.orEmpty())
        Log.d(TAG, personGivenName.orEmpty())
        Log.d(TAG, personFamilyName.orEmpty())
        Log.d(TAG, personEmail.orEmpty())
        Log.d(TAG, personId.orEmpty())
        Log.d(TAG, personPhoto.toString())

        mAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                updateUI(User(acct.id!!, acct.email!!, ""))
            } else {
                Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun validateFillParameters(): Boolean =
        !(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))

    private fun updateUI(user: User) {
        startActivity(HomeActivity.createIntent(this, user))
    }

    companion object {
        private val TAG = "LoginActivity"
        private val RC_SIGN_IN: Int = 1
    }
}
