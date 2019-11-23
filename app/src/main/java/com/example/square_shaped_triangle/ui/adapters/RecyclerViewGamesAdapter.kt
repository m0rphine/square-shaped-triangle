package com.example.square_shaped_triangle.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.database.Game
import kotlinx.android.synthetic.main.item_game.view.*

class RecyclerViewGamesAdapter(
    private val games: ArrayList<Game>,
    private val clickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerViewGamesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_game,
                parent,
                false
            ), clickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = games.size

    private fun getItem(position: Int): Game = games[position]

    class ViewHolder(
        view: View,
        listener: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        val textViewName = view.item_game_textView_name
        val textViewYear = view.item_game_textView_year
        val textViewDesigner = view.item_game_textView_designer
        val textViewMaxMinPlayers = view.item_game_textView_num_players
        val textViewTime = view.item_game_textView_game_time
        val textViewAge = view.item_game_textView_player_age


        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener(position)
                }
            }
        }

        fun bind(game: Game) {
            textViewName.text = game.name
            textViewYear.text = game.yearPublished.toString()
            textViewDesigner.text = game.publisher
            textViewMaxMinPlayers.text = "${game.minPlayers}-${game.maxPlayers}"
            textViewTime.text = game.maxPlayTime.toString()
            textViewAge.text = "${game.minAge}+"

        }
    }
}