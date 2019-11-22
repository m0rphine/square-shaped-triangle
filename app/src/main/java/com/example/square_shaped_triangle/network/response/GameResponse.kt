package com.example.square_shaped_triangle.network.response

import android.net.Uri
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class GameResponse(
    @Json(name = "id")
    val id: String,

    @Json(name = "name")
    val name: String,

    @Json(name = "year_published")
    val yearPublished: Int,

    @Json(name = "min_players")
    val minPlayers: Int,

    @Json(name = "max_players")
    val maxPlayers: Int,

    @Json(name = "max_playtime")
    val maxPlayTime: Int,

    @Json(name = "min_age")
    val minAge: Int,

    @Json(name = "description")
    val description: String,

    @Json(name = "description")
    val imageUrl: Uri,

    @Json(name = "primary_publisher")
    val publisher: String,

    @Json(name = "mechanics")
    val mechanics: List<GameMechanicsResponse>,

    @Json(name = "categories")
    val categories: List<GameCategoriesResponse>,

    @Json(name = "average_user_rating")
    val averageRating: Double
)