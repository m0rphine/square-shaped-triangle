package com.example.square_shaped_triangle.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.database.Game
import kotlinx.android.synthetic.main.item_game.view.*

class RecyclerViewGamesAdapter(private val games: ArrayList<Game>) :
    RecyclerView.Adapter<RecyclerViewGamesAdapter.GamesViewHolder>() {
    class GamesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textViewGameName = view.item_game_textView_name

        fun bind(game: Game) {
            textViewGameName.text = game.name
        }
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GamesViewHolder(view)
    }

    override fun getItemCount() = games.size

    fun getItem(position: Int): Game = games[position]
}