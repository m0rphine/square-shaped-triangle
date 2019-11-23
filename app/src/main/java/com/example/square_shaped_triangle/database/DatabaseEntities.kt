package com.example.square_shaped_triangle.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class Game constructor(
    @PrimaryKey
    val id: String,
    val name: String?,
    val yearPublished: Int?,
    val minPlayers: Int?,
    val maxPlayers: Int?,
    val maxPlayTime: Int?,
    val minAge: Int?,
    val description: String?,
    val imageUrl: String?,
    val publisher: String?,
    val averageRating: Double?
)

@Entity
data class User constructor(
    @PrimaryKey
    val id: String,
    val name: String
)

class FavoriteGames {
    @Embedded
    @JvmField
    var user: User? = null

    @Relation(parentColumn = "id", entityColumn = "id", entity = Game::class)
    @JvmField
    var games: List<Game> = emptyList()
}

class OwnedGames {
    @Embedded
    @JvmField
    var user: User? = null

    @Relation(parentColumn = "id", entityColumn = "id", entity = Game::class)
    @JvmField
    var games: List<Game> = emptyList()
}

@Entity
data class Event constructor(
    @PrimaryKey
    val id: String,
    val name: String,
    val address: String,
    val minPlayers: Int,
    val maxPlayers: Int,
    val date: String,
    val creatorId: String,
    val state: String
)

class Players {
    @Embedded
    @JvmField
    var event: Event? = null

    @Relation(parentColumn = "id", entityColumn = "id", entity = User::class)
    @JvmField
    var players: List<User> = emptyList()
}

class EventGames {
    @Embedded
    @JvmField
    var event: Event? = null

    @Relation(parentColumn = "id", entityColumn = "id", entity = Game::class)
    @JvmField
    var games: List<Game> = emptyList()
}