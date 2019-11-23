package com.example.square_shaped_triangle.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.data.User
import kotlinx.android.synthetic.main.item_player.view.*

class RecyclerViewPlayersAdapter(val players: ArrayList<User>) :
    RecyclerView.Adapter<RecyclerViewPlayersAdapter.PlayerViewHolder>() {

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewPlayerName = itemView.itemPlayer_textView_playerName

        fun bind(player: User) {
            textViewPlayerName.text = player.email
        }

    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_player,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = players.size

    fun getItem(position: Int): User = players[position]
}