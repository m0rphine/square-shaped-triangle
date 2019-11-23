package com.example.square_shaped_triangle.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.database.Event
import kotlinx.android.synthetic.main.item_event.view.*

class RecyclerViewEventsAdapter(
    private val events: ArrayList<Event>,
    private val clickListener: (position: Int) -> Unit
) :
    RecyclerView.Adapter<RecyclerViewEventsAdapter.EventViewHolder>() {
    class EventViewHolder(val view: View, listener: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(view) {
        val textViewEventName = view.item_event_name
        val textViewEventAddress = view.item_event_city
        val textViewEventCountGames = view.item_event_games
        val textViewEventMaxMinPeople = view.item_event_people_int
        val textViewEventPeople = view.item_event_people
        val textViewEventDate = view.item_event_date
        val textViewEventTime = view.item_event_time
        val textViewEventCreater = view.item_event

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener(position)
                }
            }
        }


        fun bind(event: Event) {
            textViewEventName.text = event.name
            textViewEventAddress.text = event.address
            //textViewEventCountGames.text = event. TODO: get Count games
            textViewEventMaxMinPeople.text =
                event.minPlayers.toString() + " - " + event.maxPlayers.toString()
            // textViewEventPeople.text = event.  TODO: get Count Of People
            textViewEventDate.text = event.date
            //textViewEventTime = event.time   TODO: create field time for user
            // textViewEventCreater = event.creatorId  TODO: getUserbyId
        }
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return EventViewHolder(parent, clickListener)
    }

    override fun getItemCount() = events.size

    fun getItem(position: Int): Event = events[position]
}