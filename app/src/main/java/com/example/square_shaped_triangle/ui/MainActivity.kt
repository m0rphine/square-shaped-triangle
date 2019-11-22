package com.example.square_shaped_triangle.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.square_shaped_triangle.R
import kotlinx.android.synthetic.main.activity_register.*
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {

    companion object {
        const val PROFILE = 0
        const val EVENTS = 1
        const val GAMES = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        bottomNavigation.setOnNavigationItemReselectedListener {
            val fragment: Fragment = when (it.itemId) {
                 PROFILE -> Fragment()
                 EVENTS -> Fragment()
                 GAMES -> Fragment()
                else -> throw IllegalArgumentException()
            }
            supportFragmentManager.beginTransaction()
                .add(R.id.frameContainer, fragment)
                .commit()
        }
    }
}
