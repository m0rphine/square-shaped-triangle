package com.example.square_shaped_triangle.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.data.User
import com.example.square_shaped_triangle.database.Game
import com.example.square_shaped_triangle.ui.adapters.RecyclerViewGamesAdapter
import com.example.square_shaped_triangle.ui.adapters.RecyclerViewPlayersAdapter
import kotlinx.android.synthetic.main.activity_event_info.*

class EventDetailsActivity : AppCompatActivity() {

    val players: ArrayList<User> = ArrayList()
    val games: ArrayList<Game> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_info)

        generateUsers()

        activityEventInfo_recyclerView_listPlayers.adapter = RecyclerViewPlayersAdapter(players)
        activityEventInfo_recyclerView_listPlayers.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        generateGames()

        activityEventInfo_recyclerView_listGames.adapter = RecyclerViewGamesAdapter(games)
        activityEventInfo_recyclerView_listGames.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    fun generateUsers() {
        players.add(User("dsfhsjdhf", "JohnDoe", "11111111"))
        players.add(User("dsfhsjdhf", "JohnDoe", "11111111"))
        players.add(User("dsfhsjdhf", "JohnDoe", "11111111"))
        players.add(User("dsfhsjdhf", "JohnDoe", "11111111"))
        players.add(User("dsfhsjdhf", "JohnDoe", "11111111"))
        players.add(User("dsfhsjdhf", "JohnDoe", "11111111"))
        players.add(User("dsfhsjdhf", "JohnDoe", "11111111"))

    }

    fun generateGames() {
        games.add(Game("dshcdhjgfjsa", "Catan"))
        games.add(Game("dshcdhjgfjsa", "Catan"))
        games.add(Game("dshcdhjgfjsa", "Catan"))
        games.add(Game("dshcdhjgfjsa", "Catan"))
        games.add(Game("dshcdhjgfjsa", "Catan"))
        games.add(Game("dshcdhjgfjsa", "Catan"))
        games.add(Game("dshcdhjgfjsa", "Catan"))

    }
}