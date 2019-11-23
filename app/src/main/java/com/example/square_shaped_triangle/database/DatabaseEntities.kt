package com.example.square_shaped_triangle.database

import androidx.room.Entity
import androidx.room.PrimaryKey

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
data class Event constructor(
    @PrimaryKey
    val id: String,
    val name: String?,
    val address: String?,
    val minPlayers: Int?,
    val maxPlayers: Int?,
    val date: String?,
    val creatorId: String?,
    val state: String?,
    val game: String?
)
