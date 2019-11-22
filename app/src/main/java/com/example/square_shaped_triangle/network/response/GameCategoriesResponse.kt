package com.example.square_shaped_triangle.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class GameCategoriesResponse(
    @Json(name = "id")
    val id: String
)