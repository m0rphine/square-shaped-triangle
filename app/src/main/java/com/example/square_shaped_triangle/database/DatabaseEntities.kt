package com.example.square_shaped_triangle.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Game constructor(
    @PrimaryKey
    val id: String,
    val name: String,
    val isFavorite: Boolean,
    val isHas: Boolean
)

@Entity
data class User constructor(
    @PrimaryKey
    val id: String,
    val name: String,
    val games: List<Game>
)

@Entity
data class Event constructor(
    val id: String,
    val name: String,
    val address: String,
    val minPlayers: Int,
    val maxPlayers: Int,
    val date: String,
    val creator: User,
    val state: String,
    val players: List<User>,
    val games: List<Game>
    )