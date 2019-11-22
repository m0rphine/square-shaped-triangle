package com.example.square_shaped_triangle.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.square_shaped_triangle.R

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