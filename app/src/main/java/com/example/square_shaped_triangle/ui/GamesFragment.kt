package com.example.square_shaped_triangle.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.ui.adapters.GamesAdapter
import com.example.square_shaped_triangle.viewmodels.AppViewModel
import kotlinx.android.synthetic.main.fragment_board_games.*

class GamesFragment : Fragment() {

    private lateinit var viewModel: AppViewModel

    private lateinit var adapter: GamesAdapter

    companion object {
        fun newInstance() = GamesFragment()
        val GAMES_ID = "GAMES_ID"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_board_games, container, false)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = ViewModelProviders.of(this.requireActivity()).get(AppViewModel::class.java)
        viewModel.getGames()
        adapter = GamesAdapter {position ->

            val gameResponse = adapter.getItem(position)
            val intent = Intent(this.context, GameDetailsActivity::class.java)
            intent.putExtra("GAME_ID", gameResponse.id)
            startActivity(intent)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentBoardGames_recyclerView_listGames.adapter = adapter
        fragmentBoardGames_recyclerView_listGames.layoutManager = LinearLayoutManager(this.context)
        viewModel.games.observe(this, Observer {
            adapter.list = it.games
            adapter.notifyDataSetChanged()
        })
        context.let {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { viewModel.getGameByName(it) }
                    viewModel.gameByName.observe(this@GamesFragment, Observer {
                        adapter.list = it.games
                        adapter.notifyDataSetChanged()
                    })
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(TextUtils.isEmpty(newText)) {
                        viewModel.games.observe(this@GamesFragment, Observer {
                            adapter.list = it.games
                            adapter.notifyDataSetChanged()
                        })
                    }
                    return true
                }

            })
        }
    }
}