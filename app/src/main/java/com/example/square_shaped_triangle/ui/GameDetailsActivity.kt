package com.example.square_shaped_triangle.ui

import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.database.FavoriteGame
import com.example.square_shaped_triangle.network.response.GameResponse
import com.example.square_shaped_triangle.viewmodels.AppViewModel
import kotlinx.android.synthetic.main.activity_game_info.*

class GameDetailsActivity : AppCompatActivity() {


    private lateinit var viewModel: AppViewModel

    private lateinit var gameResponse: GameResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_info)
        val gameId = intent.getStringExtra("GAME_ID")
        viewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)
        viewModel.gameById.observe(this, Observer {
            gameResponse = it.games.first()
            setView(gameResponse)
        })
        viewModel.getGameById(gameId)

        floatingActionButton_addToFavorites.setOnClickListener {
            createvent(gameResponse)
            Toast.makeText(this, "Added to Favorites.", Toast.LENGTH_LONG).show()
        }
    }

    fun setView(it: GameResponse){
        imageView2.load(it.imageUrl)
        gameInfoFragment_textView_GameName.text = it.name
        gameInfoFragment_textView_yearPublisher.text = it.yearPublished.toString()
        gameInfoFragment_textView_publisher.text = it.publisher
        gameInfoFragment_textView_min_players.text = it.minPlayers.toString()
        gameInfoFragment_textView_max_players.text = it.maxPlayers.toString()
        //gameInfoFragment_textView_min_playtime.text = it.maxPlayTime
        gameInfoFragment_textView_age.text = "${it.minAge}+"
        gameInfoFragment_textViewl_description.text = Html.fromHtml(it.description)

    }

    fun createvent(it: GameResponse) {
        viewModel.addFavorite(
            favoriteGame =
            FavoriteGame(
                it.id,
                it.name,
                it.yearPublished,
                it.minPlayers,
                it.maxPlayers,
                it.maxPlayTime,
                it.minAge,
                it.description,
                it.imageUrl,
                it.publisher
            )
        )
    }
}