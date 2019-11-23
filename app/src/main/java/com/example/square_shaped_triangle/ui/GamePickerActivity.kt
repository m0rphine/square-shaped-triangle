package com.example.square_shaped_triangle.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.ui.adapters.GamesAdapter
import com.example.square_shaped_triangle.viewmodels.AppViewModel
import kotlinx.android.synthetic.main.fragment_board_games.*

class GamePickerActivity : AppCompatActivity() {

    private lateinit var viewModel: AppViewModel

    private lateinit var adapter: GamesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_board_games)

        viewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)
        viewModel.getGames()
        adapter = GamesAdapter { position ->
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra(KEY_PICKED_GAME_NAME, adapter.getItem(position).name)
            })
            finish()
        }

        fragmentBoardGames_recyclerView_listGames.adapter = adapter
        fragmentBoardGames_recyclerView_listGames.layoutManager = LinearLayoutManager(this)
        viewModel.games.observe(this, Observer {
            adapter.list = it.games
            adapter.notifyDataSetChanged()
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.getGameByName(it) }
                viewModel.gameByName.observe(this@GamePickerActivity, Observer {
                    adapter.list = it.games
                    adapter.notifyDataSetChanged()
                })
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (TextUtils.isEmpty(newText)) {
                    viewModel.games.observe(this@GamePickerActivity, Observer {
                        adapter.list = it.games
                        adapter.notifyDataSetChanged()
                    })
                }
                return true
            }

        })
    }

    companion object {

        const val KEY_PICKED_GAME_NAME = "KEY_PICKED_GAME_NAME"
    }
}
