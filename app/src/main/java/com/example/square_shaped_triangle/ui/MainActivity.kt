package com.example.square_shaped_triangle.ui

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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemReselectedListener {
            val fragment: Fragment = when (it.itemId) {
                 PROFILE -> ProfileFragment.newInstance()
                 EVENTS -> EventsFragment.newInstance()
                 GAMES -> GamesFragment.newInstance()
                else -> throw IllegalArgumentException()
            }
            supportFragmentManager.beginTransaction()
                .add(R.id.frameContainer, fragment)
                .commit()
        }
    }
}
