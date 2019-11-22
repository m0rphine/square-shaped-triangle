package com.example.square_shaped_triangle.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.data.User
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    //UI elements
    private lateinit var etLogin: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnCreateAccount: Button

    //Firebase reference
    private lateinit var mAuth: FirebaseAuth

    private lateinit var login: String
    private lateinit var email: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initialise()
    }

    private fun initialise() {
        etLogin = findViewById<View>(R.id.login_register) as EditText
        etEmail = findViewById<View>(R.id.email_register) as EditText
        etPassword = findViewById<View>(R.id.password_register) as EditText
        btnCreateAccount = findViewById<View>(R.id.register_button) as Button
        mAuth = FirebaseAuth.getInstance()
        btnCreateAccount.setOnClickListener { createNewAccount() }
    }

    private fun createNewAccount() {
        login = etLogin.text.toString()
        email = etEmail.text.toString()
        password = etPassword.text.toString()

        if (validateFillParameters()) {
            createUserWithEmailAndPassword()
        } else {
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateFillParameters(): Boolean =
        !(TextUtils.isEmpty(login) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password))

    private fun createUserWithEmailAndPassword() {

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) { // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    verifyEmail() //Verify Email
                    val user = User(
                        mAuth.currentUser?.uid.orEmpty(),
                        email,
                        password
                    )
                    updateUserInfoAndUI(user) //update user profile information
                } else { // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        this, "Authentication failed.", Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun updateUserInfoAndUI(user: User) {
        startActivity(
            HomeActivity.createIntent(
                this,
                user
            )
        )
    }

    private fun verifyEmail() {
        val mUser = mAuth.currentUser;
        mUser?.sendEmailVerification()?.addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    this,
                    "Verification email sent to " + mUser.getEmail(),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Log.e(TAG, "sendEmailVerification", task.exception)
                Toast.makeText(
                    this, "Failed to send verification email.", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        private val TAG = "RegisterActivity"
    }
}
