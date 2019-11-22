package com.example.square_shaped_triangle.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.square_shaped_triangle.database.getDatabase
import com.example.square_shaped_triangle.network.NetworkGamesModule
import com.example.square_shaped_triangle.network.response.GamesListResponse
import com.example.square_shaped_triangle.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val appRepository = Repository(getDatabase(application))

    private val _games = MutableLiveData<GamesListResponse>()
    val games: LiveData<GamesListResponse>
        get() = _games


    fun getGames() {
        viewModelScope.launch {
            _games.value = NetworkGamesModule.getGames()
        }
    }

}