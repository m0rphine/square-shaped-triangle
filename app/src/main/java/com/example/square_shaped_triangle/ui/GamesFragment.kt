package com.example.square_shaped_triangle.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.square_shaped_triangle.R
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


}