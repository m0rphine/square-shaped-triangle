package com.example.square_shaped_triangle.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.activity.helpers.UserSharedPreferenceHelper
import com.example.square_shaped_triangle.database.Event
import com.example.square_shaped_triangle.database.FavoriteGame
import com.example.square_shaped_triangle.database.Game
import com.example.square_shaped_triangle.viewmodels.AppViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.item_game.view.*

class ProfileFragment : Fragment() {

    private lateinit var viewModel: AppViewModel

    private lateinit var myGames: List<Game>
    private lateinit var favoriteGames: List<Game>
    private lateinit var events: List<Event>

    private lateinit var adapter: FavoritesGamesAdapter

    companion object {
        fun newInstance() = ProfileFragment()
        val PROFILE_ID = "profile_id"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        viewModel.ownedGames.observe(this, Observer { myGames = it.games})
//        viewModel.favoriteGames.observe(this, Observer { favoriteGames = it.games })
//        viewModel.events.observe(this, Observer { events = it })
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProviders.of(this.requireActivity()).get(AppViewModel::class.java)
        adapter = FavoritesGamesAdapter {position->
            val gameResponse = adapter.getItem(position)
            val intent = Intent(this.context, GameDetailsActivity::class.java)
            intent.putExtra("GAME_ID", gameResponse.id)
            startActivity(intent)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        profile_recyclerView.adapter = adapter
        profile_recyclerView.layoutManager = LinearLayoutManager(this.context)
        context?.let {
            Log.i("name U", UserSharedPreferenceHelper.newInstance(it).name.orEmpty())
            profile_textView_nickname.text = UserSharedPreferenceHelper.newInstance(it).name
            //profile_button_avatar.load(UserSharedPreferenceHelper.newInstance(it).uri)
            profile_floating_action_button.setOnClickListener {
                TODO()
            }
            viewModel.favoriteGames.observe(this, Observer {
                adapter.list = it
                adapter.notifyDataSetChanged()
            })
        }
    }
}

class FavoritesGamesAdapter(
    private val clickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<FavoritesGamesAdapter.GamesViewHolder>() {

    var list: List<FavoriteGame> = emptyList()

    class GamesViewHolder(val view: View, private val clickListener: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(view) {
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
        holder.view.apply {
            val game = list[position]
            item_game_imageView.load(game.imageUrl) {
                crossfade(true)
            }
            holder.view.apply {
                item_game_textView_name.text = game.name
                item_game_textView_year.text = game.yearPublished.toString()
                item_game_textView_num_players.text = "${game.minPlayers}-${game?.maxPlayers}"
                item_game_textView_game_time.text = "${game.maxPlayTime}"
                item_game_textView_player_age.text = "${game.minAge}+"
            }

        }
    }

    fun getItem(position: Int): FavoriteGame = list[position]
    override fun getItemCount() = list.size
}
