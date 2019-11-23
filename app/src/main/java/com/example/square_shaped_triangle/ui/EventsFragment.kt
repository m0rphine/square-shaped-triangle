package com.example.square_shaped_triangle.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.database.Event
import com.example.square_shaped_triangle.ui.adapters.RecyclerViewEventsAdapter
import kotlinx.android.synthetic.main.fragment_events.*

class EventsFragment: Fragment() {

    val events = ArrayList<Event>()

    companion object{
        fun newInstance() = EventsFragment()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_events, container, false)

        //generateEvents()

        events_recyclerView.adapter = RecyclerViewEventsAdapter(events) { position ->
            val event = events.get(position)
            //TODO:
        }
        events_recyclerView.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)


        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        context.let {
            events_floating_action_button.setOnClickListener {
                TODO()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        TODO()
    }
}