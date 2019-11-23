package com.example.square_shaped_triangle.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class MechanicsListResponse(
    @Json(name = "mechanics")
    val mechanics: List<MechanicsResponse>?
)