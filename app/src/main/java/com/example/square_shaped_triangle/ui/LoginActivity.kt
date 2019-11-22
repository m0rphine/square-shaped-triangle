package com.example.square_shaped_triangle.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.activity.helpers.singincontroller.EventCallback
import com.example.square_shaped_triangle.activity.helpers.singincontroller.GoogleSignInController
import com.example.square_shaped_triangle.activity.helpers.ValidationHelper.validateEmailAndPassword
import com.example.square_shaped_triangle.activity.helpers.singincontroller.EmailSignInController
import com.example.square_shaped_triangle.data.User
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    //UI elements
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnCreateAccount: Button
    private lateinit var google_button: SignInButton

    private val eventCallback = object :
        EventCallback {
        override fun onError(error: String) {
            Toast.makeText(this@LoginActivity, error, Toast.LENGTH_LONG).show()
        }

        override fun startActivityForResult(signInIntent: Intent) {
            this@LoginActivity.startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        override fun updateUI() {
            startActivity(HomeActivity.createIntent(this@LoginActivity))
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
        initialise()
        googleSignInController.configureGoogleSignIn()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN && resultCode == Activity.RESULT_OK) {
            googleSignInController.handleGoogleSignIn(data)
        }
    }

    private fun initialise() {
        etEmail = findViewById<View>(R.id.login_input_email) as EditText
        etPassword = findViewById<View>(R.id.login_input_password) as EditText
        btnLogin = findViewById<View>(R.id.button_login) as Button
        btnCreateAccount = findViewById<View>(R.id.create_account_button) as Button
        google_button = findViewById<View>(R.id.google_button) as SignInButton
        btnCreateAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        btnLogin.setOnClickListener { emailSignInController.loginUser(etEmail.text.toString(), etPassword.text.toString()) }
        google_button.setOnClickListener { googleSignInController.signIn() }
    }

    companion object {
        private val TAG = "LoginActivity"
        private val RC_SIGN_IN: Int = 1

        fun createIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}
