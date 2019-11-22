package com.example.square_shaped_triangle.network.service

import com.example.square_shaped_triangle.network.response.GamesListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

private const val CLIENT_ID = "sQexCsxQFb"
private const val POPULARITY = "popularity"

interface GameApiService {
    @GET("search")
    fun getGames(
        @Query("client_id") client_id : String = CLIENT_ID
    ): Call<GamesListResponse>

    @GET("search")
    fun getPopularGames(
        @Query("order_by") order_by : String = POPULARITY,
        @Query("client_id") client_id : String = CLIENT_ID
    ): Call<GamesListResponse>

    @GET("search")
    fun getGameById(
        @Query("ids") id: String,
        @Query("client_id") client_id : String = CLIENT_ID
    ): Call<GamesListResponse>

    @GET("search")
    fun getGameByName(
        @Query("name") name: String,
        @Query("client_id") client_id : String = CLIENT_ID
    ): Call<GamesListResponse>

    @GET("game/mechanics")
    fun getMechanics(
        @Query("client_id") client_id : String = CLIENT_ID
    ): Call<GamesListResponse>

}