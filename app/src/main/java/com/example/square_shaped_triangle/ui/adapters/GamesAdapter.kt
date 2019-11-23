package com.example.square_shaped_triangle.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.network.response.GameResponse
import kotlinx.android.synthetic.main.item_game.view.*

class GamesAdapter(private val clickListener: (position: Int) -> Unit
): RecyclerView.Adapter<GamesAdapter.GamesViewHolder>() {

    var list: List<GameResponse> = emptyList()

    class GamesViewHolder(val view: View, private val clickListener: (position: Int) -> Unit) : RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickListener(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GamesViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val game = list.get(position)
        holder.view.apply{
            item_game_textView_name.text = game.name
            item_game_textView_year.text = game.yearPublished.toString()
            item_game_textView_num_players.text = "${game.minPlayers}-${game?.maxPlayers}"
            item_game_textView_game_time.text = "${game.maxPlayTime}"
            item_game_textView_player_age.text = "${game.minAge}+"
        }

    }

    override fun getItemCount() = list.size
}