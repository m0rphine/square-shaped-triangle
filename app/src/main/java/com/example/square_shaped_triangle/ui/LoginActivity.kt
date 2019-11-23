package com.example.square_shaped_triangle.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.activity.helpers.singincontroller.EmailSignInController
import com.example.square_shaped_triangle.activity.helpers.singincontroller.EventCallback
import com.example.square_shaped_triangle.activity.helpers.singincontroller.GoogleSignInController
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    //UI elements
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnCreateAccount: Button
    private lateinit var googleButton: Button

    private val eventCallback = object :
        EventCallback {
        override fun onError(error: String) {
            Toast.makeText(this@LoginActivity, error, Toast.LENGTH_LONG).show()
        }

        override fun startActivityForResult(signInIntent: Intent) {
            this@LoginActivity.startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        override fun updateUI() {
            startActivity(MainActivity.createIntent(this@LoginActivity))
        }
    }

    private val googleSignInController =
        GoogleSignInController(
            eventCallback,
            this@LoginActivity
        )

    private val emailSignInController =
        EmailSignInController(
            eventCallback,
            this@LoginActivity
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        googleSignInController.configureGoogleSignIn()
        initialise()

    }

    override fun onStart() {
        super.onStart()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            eventCallback.updateUI()
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            googleSignInController.handleGoogleSignIn(data)
        }
    }

    private fun initialise() {
        etEmail = findViewById<View>(R.id.login_input_email) as EditText
        etPassword = findViewById<View>(R.id.login_input_password) as EditText
        btnLogin = findViewById<View>(R.id.button_login) as Button
        btnCreateAccount = findViewById<View>(R.id.create_account_button) as Button
        googleButton = findViewById<View>(R.id.google_button) as Button
        btnCreateAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        btnLogin.setOnClickListener {
            emailSignInController.loginUser(
                etEmail.text.toString(),
                etPassword.text.toString()
            )
        }
        googleButton.setOnClickListener {
            googleSignInController.signIn()
        }
    }

    companion object {
        private val TAG = "LoginActivity"
        private val RC_SIGN_IN: Int = 1

    }
}
