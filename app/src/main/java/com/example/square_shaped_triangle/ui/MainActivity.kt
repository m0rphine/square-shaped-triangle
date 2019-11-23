package com.example.square_shaped_triangle.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.square_shaped_triangle.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val PROFILE = 0
        const val EVENTS = 1
        const val GAMES = 2

        fun createIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.frameContainer,ProfileFragment.newInstance())
            .commit()

        bottomNavigation.setOnNavigationItemReselectedListener {
            val fragment: Fragment = when (it.itemId) {
                 PROFILE -> ProfileFragment.newInstance()
                 EVENTS -> EventsFragment.newInstance()
                 GAMES -> GamesFragment.newInstance()
                else -> throw IllegalArgumentException()
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameContainer, fragment)
                .commit()
        }
    }
}
