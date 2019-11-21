package com.example.square_shaped_triangle.activity

import android.content.Intent
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

class LoginActivity : AppCompatActivity() {
    private var email: String? = null
    private var password: String? = null

    //UI elements
    private var etEmail: EditText? = null
    private var etPassword: EditText? = null
    private var btnLogin: Button? = null
    private var btnCreateAccount: Button? = null

    //Firebase references
    private var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initialise()
    }

    private fun initialise() {
        etEmail = findViewById<View>(R.id.login_input_email) as EditText
        etPassword = findViewById<View>(R.id.login_input_password) as EditText
        btnLogin = findViewById<View>(R.id.button_login) as Button
        btnCreateAccount = findViewById<View>(R.id.create_account_button) as Button
        mAuth = FirebaseAuth.getInstance()

        btnCreateAccount!!.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnLogin!!.setOnClickListener { loginUser() }
    }

    private fun loginUser() {
        email = etEmail?.text.toString()
        password = etPassword?.text.toString()

        if (validateFillParameters()) {
            Log.d(TAG, "Logging in user.")
            mAuth!!.signInWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithEmail:success")
                        val user = User(
                            mAuth?.currentUser?.uid.orEmpty(),
                            "Irina",
                            email.orEmpty(),
                            password.orEmpty()
                        )
                        updateUI()
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



    private fun validateFillParameters(): Boolean =
        !(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))

    private fun updateUI() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    companion object {
        private val TAG = "LoginActivity"
    }
}
