package com.example.square_shaped_triangle.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.database.Game
import com.example.square_shaped_triangle.ui.adapters.RecyclerViewGamesAdapter
import kotlinx.android.synthetic.main.activity_event_info.*
import kotlinx.android.synthetic.main.fragment_board_games.*

class GamesFragment: Fragment() {

    val games = ArrayList<Game>()

    companion object{
        fun newInstance() = GamesFragment()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_board_games, container, false)

        //generateGames()

        activityEventInfo_recyclerView_listGames.adapter =
            RecyclerViewGamesAdapter(games) { position ->
                val game = games.get(position)
                //TODO:
            }
        activityEventInfo_recyclerView_listGames.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)


        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        context.let {
           searchView.setOnSearchClickListener {
               TODO()
           }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        TODO()
    }


}