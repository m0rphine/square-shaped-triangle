package com.example.square_shaped_triangle.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.ui.adapters.EventsAdapter
import com.example.square_shaped_triangle.viewmodels.AppViewModel
import kotlinx.android.synthetic.main.fragment_events.*

class EventsFragment: Fragment() {

    private lateinit var viewModel: AppViewModel

    private lateinit var adapter: EventsAdapter


    companion object{
        fun newInstance() = EventsFragment()
        val EVENTS_ID = "EVENTS_ID"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProviders.of(this.requireActivity()).get(AppViewModel::class.java)
        adapter = EventsAdapter{}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        events_recyclerView.adapter = adapter
        events_recyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        viewModel.events.observe(this, Observer {
            adapter.list = it
            adapter.notifyDataSetChanged()
        })
        context.let {
            events_floating_action_button.setOnClickListener {
                startActivity(Intent(this.context, CreateEventActivity::class.java))
            }
        }
    }
}