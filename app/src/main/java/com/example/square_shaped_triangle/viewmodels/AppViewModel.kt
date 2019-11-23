package com.example.square_shaped_triangle.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.square_shaped_triangle.database.Event
import com.example.square_shaped_triangle.database.FavoriteGame
import com.example.square_shaped_triangle.database.getDatabase
import com.example.square_shaped_triangle.network.NetworkGamesModule
import com.example.square_shaped_triangle.network.response.CategoriesListResponse
import com.example.square_shaped_triangle.network.response.GamesListResponse
import com.example.square_shaped_triangle.network.response.MechanicsListResponse
import com.example.square_shaped_triangle.repository.Repository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.callbackFlow

class AppViewModel(application: Application) : AndroidViewModel(application) {

    /*
    //How we should Use LiveData in Activity
    var viewModel = AppViewModel(application)
        viewModel.getGames()
        viewModel.games.observe(this, Observer { games ->
            Log.i(TAG + " games =  ", games.toString())
        })
    */

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    private val appRepository = Repository(getDatabase(application))

    private val _games = MutableLiveData<GamesListResponse>()
    val games: LiveData<GamesListResponse>
        get() = _games

    val events: LiveData<List<Event>>
        get() = appRepository.events

    private val _gameByName = MutableLiveData<GamesListResponse>()
    val gameByName: LiveData<GamesListResponse>
        get() = _gameByName

    private val _gameById = MutableLiveData<GamesListResponse>()
    val gameById: LiveData<GamesListResponse>
        get() = _gameById

    private val _mechanics = MutableLiveData<MechanicsListResponse>()
    val mechanics: LiveData<MechanicsListResponse>
        get() = _mechanics

    private val _categories = MutableLiveData<CategoriesListResponse>()
    val categories: LiveData<CategoriesListResponse>
        get() = _categories

    val favoriteGames: LiveData<List<FavoriteGame>>
        get() = appRepository.favoriteGames

    fun getGames() {
        viewModelScope.launch {
            _games.postValue(NetworkGamesModule.getGames())
        }
    }

    fun getGameByName(name: String) {
        viewModelScope.launch {
            _gameByName.postValue(NetworkGamesModule.getGameByName(name))
        }
    }

    fun getGameById(id: String) {
        viewModelScope.launch {
            _gameById.postValue(NetworkGamesModule.getGameById(id))
        }
    }

//    fun getEventById(id: String):LiveData<Event> {
//        return appRepository.getEventById(id)
//    }

    fun getMechanics() {
        viewModelScope.launch {
            _mechanics.postValue(NetworkGamesModule.getMechanics())
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            _categories.postValue(NetworkGamesModule.getCategories())
        }
    }

    fun addEvent(event: Event, callback: () -> Unit) {
        viewModelScope.launch {
            appRepository.addEvent(event)
            withContext(Dispatchers.Main) { callback() }
        }
    }

    fun addFavorite(favoriteGame: FavoriteGame) {
        viewModelScope.launch {
            appRepository.addFavorite(favoriteGame)
        }
    }
}