package com.example.square_shaped_triangle.repository

import androidx.lifecycle.LiveData
import com.example.square_shaped_triangle.database.AppDatabase
import com.example.square_shaped_triangle.database.Event
import com.example.square_shaped_triangle.database.FavoriteGame

class Repository(private val database: AppDatabase) {


    val events: LiveData<List<Event>> = database.dao.getEvent()
    val favoriteGames: LiveData<List<FavoriteGame>> = database.dao.getFavoriteGames()


    /*fun favoriteGames(userId: String): LiveData<FavoriteGames> =
        database.dao.getFavoriteGames(userId)

    fun setFavoriteGame(favoriteGames: FavoriteGames) =
        database.dao.insertFavoriteGame(favoriteGames)

    fun ownedGames(userId: String): LiveData<OwnedGames> =
        database.dao.getOwnedGames(userId)

    fun setOwnedGame(ownedGames: OwnedGames) = database.dao.insertOwnedGame(ownedGames)

    fun getPlayers(eventId: String): LiveData<Players> =
        database.dao.getPlayers(eventId)

    fun getEventGames(eventId: String): LiveData<EventGames> =
        database.dao.getEventGames(eventId)

    fun setUser(user: User) = database.dao.insertUser(user)
    fun setPlayer(players: Players) = database.dao.insertPlayers(players)*/

    fun addEvent(event: Event) = database.dao.insertEvent(event)
    fun addFavorite(favoriteGame: FavoriteGame) = database.dao.insertFavoriteGame(favoriteGame)

//    fun getEventById(id: String):LiveData<Event> {
//        return database.dao.getEventById(id)
//    }

}