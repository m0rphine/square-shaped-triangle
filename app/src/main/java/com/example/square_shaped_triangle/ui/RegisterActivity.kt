package com.example.square_shaped_triangle.activity

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
import com.example.square_shaped_triangle.ui.HomeActivity

class RegisterActivity : AppCompatActivity() {
    //UI elements
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnCreateAccount: Button

    private val eventCallback = object :
        EventCallback {
        override fun onError(error: String) {
            Toast.makeText(this@RegisterActivity, error, Toast.LENGTH_LONG).show()
        }

        override fun startActivityForResult(signInIntent: Intent) {
        }

        override fun updateUI() {
            startActivity(HomeActivity.createIntent(this@RegisterActivity))
        }
    }

    private val emailSignInController =
        EmailSignInController(
            eventCallback,
            this@RegisterActivity
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initialise()
    }

    private fun initialise() {
        etEmail = findViewById<View>(R.id.email_register) as EditText
        etPassword = findViewById<View>(R.id.password_register) as EditText
        btnCreateAccount = findViewById<View>(R.id.register_button) as Button
        btnCreateAccount.setOnClickListener {
            emailSignInController.createNewAccount(
                etEmail.text.toString(),
                etPassword.text.toString()
            )
        }
    }

    companion object {
        private val TAG = "RegisterActivity"
    }
}
