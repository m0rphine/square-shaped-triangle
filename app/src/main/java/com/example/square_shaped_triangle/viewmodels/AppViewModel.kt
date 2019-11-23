package com.example.square_shaped_triangle.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.square_shaped_triangle.database.*
import com.example.square_shaped_triangle.network.NetworkGamesModule
import com.example.square_shaped_triangle.network.response.CategoriesListResponse
import com.example.square_shaped_triangle.network.response.GameResponse
import com.example.square_shaped_triangle.network.response.GamesListResponse
import com.example.square_shaped_triangle.network.response.MechanicsListResponse
import com.example.square_shaped_triangle.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

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

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val appRepository = Repository(getDatabase(application))

    private val _games = MutableLiveData<GamesListResponse>()
    val games: LiveData<GamesListResponse>
        get() = _games

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private val _gameByName = MutableLiveData<GameResponse>()
    val gameByName: LiveData<GameResponse>
        get() = _gameByName

    private val _gameById = MutableLiveData<GameResponse>()
    val gameById: LiveData<GameResponse>
        get() = _gameById

    private val _mechanics = MutableLiveData<MechanicsListResponse>()
    val mechanics: LiveData<MechanicsListResponse>
        get() = _mechanics

    private val _categories = MutableLiveData<CategoriesListResponse>()
    val categories: LiveData<CategoriesListResponse>
        get() = _categories

    val users: LiveData<List<User>>
        get() = appRepository.users

    val events: LiveData<List<Event>>
        get() = appRepository.events

    private var _favoriteGames = MutableLiveData<FavoriteGames>()
    val favoriteGames: LiveData<FavoriteGames>
        get() = _favoriteGames

    private var _players = MutableLiveData<Players>()
    val players: LiveData<Players>
        get() = _players

    private var _ownedGames = MutableLiveData<OwnedGames>()
    val ownedGames: LiveData<OwnedGames>
        get() = _ownedGames

    fun getGames() {
        viewModelScope.launch {
            _games.value = NetworkGamesModule.getGames()
        }
    }

    fun getGameByName(name: String) {
        viewModelScope.launch {
            _gameByName.value = NetworkGamesModule.getGameByName(name)
        }
    }

    fun getGameById(id: String) {
        viewModelScope.launch {
            _gameById.value = NetworkGamesModule.getGameById(id)
        }
    }

    fun getMechanics() {
        viewModelScope.launch {
            _mechanics.value = NetworkGamesModule.getMechanics()
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            _categories.value = NetworkGamesModule.getCategories()
        }
    }

    /*fun getFavoriteGames(userId: String) {
        viewModelScope.launch {
            _favoriteGames = appRepository.favoriteGames(userId) as MutableLiveData
        }
    }

    fun getOwnedGames(userId: String) {
        viewModelScope.launch {
            _ownedGames = appRepository.ownedGames(userId) as MutableLiveData
        }
    }

    fun getPlayers(eventId: String) {
        viewModelScope.launch {
            _players = appRepository.getPlayers(eventId) as MutableLiveData
        }
    }
*/
}