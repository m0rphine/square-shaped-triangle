package com.example.square_shaped_triangle.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.data.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user = intent.getParcelableExtra<User>(USER_OBJ)
        textView.text = user?.toString() //for testing, need to remove
        setupUI()
    }

    private fun setupUI() {
        sign_out_button.setOnClickListener {
            signOut()
        }
    }

    private fun signOut() {
        startActivity(Intent(this, RegisterActivity::class.java))
        FirebaseAuth.getInstance().signOut();
    }

    companion object {
        private val TAG = "HomeActivity"
        private val USER_OBJ = "USER_OBJ"

        fun createIntent(context: Context, user: User): Intent {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtra(USER_OBJ, user)
            return intent
        }
    }
}