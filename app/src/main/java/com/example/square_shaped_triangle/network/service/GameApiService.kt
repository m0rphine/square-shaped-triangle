package com.example.square_shaped_triangle.network.service

import com.example.square_shaped_triangle.network.response.CategoriesListResponse
import com.example.square_shaped_triangle.network.response.GameResponse
import com.example.square_shaped_triangle.network.response.GamesListResponse
import com.example.square_shaped_triangle.network.response.MechanicsListResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val CLIENT_ID = "sQexCsxQFb"
private const val POPULARITY = "popularity"

interface GameApiService {
    @GET("search")
    suspend fun getGames(
        @Query("client_id") client_id : String = CLIENT_ID
    ): GamesListResponse

    @GET("search")
    suspend fun getGameById(
        @Query("ids") id: String,
        @Query("client_id") client_id : String = CLIENT_ID
    ): GameResponse

    @GET("search")
    suspend fun getGamesByName(
        @Query("name") name: String,
        @Query("client_id") client_id : String = CLIENT_ID
    ): GamesListResponse

    @GET("game/mechanics")
    suspend fun getMechanics(
        @Query("client_id") client_id : String = CLIENT_ID
    ): MechanicsListResponse

    @GET("game/categories")
    suspend fun getCategories(
        @Query("client_id") client_id : String = CLIENT_ID
    ): CategoriesListResponse
}