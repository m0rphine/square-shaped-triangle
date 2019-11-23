package com.example.square_shaped_triangle.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.data.User
import com.example.square_shaped_triangle.database.Game
import com.example.square_shaped_triangle.ui.adapters.GamesAdapter
import com.example.square_shaped_triangle.ui.adapters.RecyclerViewPlayersAdapter
import com.example.square_shaped_triangle.viewmodels.AppViewModel
import kotlinx.android.synthetic.main.activity_event_info.*

class EventDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: AppViewModel
    val players: ArrayList<User> = ArrayList()
    val games: ArrayList<Game> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_info)

//        val eventId = intent.getStringExtra("EVENT_ID")
//        viewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)
//        viewModel.getEventById(eventId).observe(this, Observer {
//            //setView()
//        })

        activityEventInfo_recyclerView_listPlayers.adapter = RecyclerViewPlayersAdapter(players)
        activityEventInfo_recyclerView_listPlayers.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        activityEventInfo_recyclerView_listGames.adapter = GamesAdapter{}
        activityEventInfo_recyclerView_listGames.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }
}