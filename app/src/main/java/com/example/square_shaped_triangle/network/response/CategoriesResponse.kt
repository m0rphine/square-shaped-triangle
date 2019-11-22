package com.example.square_shaped_triangle.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CategoriesResponse(
    @Json(name = "id")
    val id: String,

    @Json(name = "name")
    val name: String
)