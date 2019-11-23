package com.example.square_shaped_triangle.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.database.Event
import kotlinx.android.synthetic.main.item_event.view.*
class EventsAdapter(
    private val clickListener: (position: Int) -> Unit
) :
    RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    var list: List<Event> = emptyList()

    class EventViewHolder( view: View, listener: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(view) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener(position)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = list[position]
        holder.itemView.apply {
            item_event_name.text = event.name
            item_event_city.text = event.address
            item_event_game_name.text = event.game
            item_event_people_int.text = "${event.minPlayers}-${event.maxPlayers}"
            item_event_date.text = event.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view, clickListener)
    }

    override fun getItemCount() = list.size

    fun getItem(position: Int): Event = list[position]
}