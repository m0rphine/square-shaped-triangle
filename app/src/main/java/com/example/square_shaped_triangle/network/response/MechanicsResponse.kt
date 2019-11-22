package com.example.square_shaped_triangle.network.response

import com.squareup.moshi.Json

class MechanicsResponse(
    @Json(name = "id")
    val id: String,

    @Json(name = "name")
    val name: String
)