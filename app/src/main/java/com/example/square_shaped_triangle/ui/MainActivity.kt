package com.example.square_shaped_triangle.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.ui.EventsFragment.Companion.EVENTS_ID
import com.example.square_shaped_triangle.ui.GamesFragment.Companion.GAMES_ID
import com.example.square_shaped_triangle.ui.ProfileFragment.Companion.PROFILE_ID
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var TAG = PROFILE_ID

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

        val fragmentById = supportFragmentManager.findFragmentByTag(TAG)
        if(fragmentById == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.frameContainer,ProfileFragment.newInstance(), TAG)
                .commit()
        }


        bottomNavigation.setOnNavigationItemSelectedListener {
            TAG = when (it.itemId) {
                 R.id.profile -> PROFILE_ID
                 R.id.events -> EVENTS_ID
                 R.id.games ->  GAMES_ID
                 else -> throw IllegalArgumentException()
            }
            createFragment(TAG)
            true
        }
    }

    fun createFragment(tag: String) {
        val fragmentById = supportFragmentManager.findFragmentByTag(TAG)
        fragmentById.let {
            val fragment = when(tag) {
                PROFILE_ID -> ProfileFragment.newInstance()
                EVENTS_ID -> EventsFragment.newInstance()
                GAMES_ID -> GamesFragment.newInstance()
                else -> throw IllegalArgumentException()
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameContainer, fragment, TAG)
                .commit() }
    }
}
