package com.example.square_shaped_triangle.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.database.Event
import com.example.square_shaped_triangle.database.Game
import com.example.square_shaped_triangle.viewmodels.AppViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.item_game.view.*

class ProfileFragment: Fragment() {

    private val viewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)

    private lateinit var myGames: List<Game>
    private lateinit var favoriteGames: List<Game>
    private lateinit var events: List<Event>

    companion object{
        fun newInstance() = ProfileFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.ownedGames.observe(this, Observer { myGames = it.games})
        viewModel.favoriteGames.observe(this, Observer { favoriteGames = it.games })
        viewModel.events.observe(this, Observer { events = it })
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        context.let {
            profile_button_my_games.setOnClickListener {
                profile_recyclerView.adapter = GamesAdapter(myGames)
            }
            buttonFavorites.setOnClickListener {
                profile_recyclerView.adapter = GamesAdapter(favoriteGames)
            }
            button_my_events.setOnClickListener {
                profile_recyclerView.adapter = EventsAdapter(events)
            }
            profile_floating_action_button.setOnClickListener {
                TODO()
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

    class GamesAdapter(private val dataset: List<Game>): RecyclerView.Adapter<GamesAdapter.GamesViewHolder>() {
        class GamesViewHolder(val view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
            return GamesViewHolder(view)
        }

        override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
            val game = dataset[position]
            holder.view.apply{
                item_game_textView_name.text = game.name
                item_game_textView_year.text = game.yearPublished.toString()
                item_game_textView_designer.text = game.description
                item_game_textView_num_players.text = "${game.minPlayers}-${game.maxPlayers}"
                item_game_textView_game_time.text = "$game.maxPlayTime"
                item_game_textView_player_age.text = "${game.minAge}+"
            }
        }

        override fun getItemCount() = dataset.size
    }

    class EventsAdapter(private val dataset: List<Event>): RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {
        class EventsViewHolder(val view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
            return EventsViewHolder(view)
        }

        override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItemCount() = dataset.size
    }
}