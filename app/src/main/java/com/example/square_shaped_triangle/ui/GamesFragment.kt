package com.example.square_shaped_triangle.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.database.Game
import kotlinx.android.synthetic.main.fragment_board_games.*

class GamesFragment: Fragment() {

    companion object{
        fun newInstance() = GamesFragment()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_board_games, container, false)
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

    class GamesAdapter(private val dataset: Array<Game>): RecyclerView.Adapter<GamesAdapter.GamesViewHolder>() {
        class GamesViewHolder(val view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
            return GamesViewHolder(view)
        }

        override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItemCount() = dataset.size
    }
}