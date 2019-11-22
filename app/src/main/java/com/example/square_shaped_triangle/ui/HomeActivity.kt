package com.example.square_shaped_triangle.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.activity.LoginActivity
import com.example.square_shaped_triangle.data.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        private val TAG = "HomeActivity"

        fun createIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }
}