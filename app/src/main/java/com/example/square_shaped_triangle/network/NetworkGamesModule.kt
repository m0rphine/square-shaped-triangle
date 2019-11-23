package com.example.square_shaped_triangle.network

import com.example.square_shaped_triangle.network.response.CategoriesListResponse
import com.example.square_shaped_triangle.network.response.GameResponse
import com.example.square_shaped_triangle.network.response.GamesListResponse
import com.example.square_shaped_triangle.network.response.MechanicsListResponse
import com.example.square_shaped_triangle.network.service.GameApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object NetworkGamesModule {
    private const val BASE_URL: String = "https://www.boardgameatlas.com/api/"
    private const val TEN: Long = 10
    private const val THIRTY: Long = 30

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addNetworkInterceptor(loggingInterceptor)
        .connectTimeout(TEN, TimeUnit.SECONDS)
        .writeTimeout(THIRTY, TimeUnit.SECONDS)
        .readTimeout(THIRTY, TimeUnit.SECONDS)
        .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val gameApiService: GameApiService = retrofit.create()

    suspend fun getGames(): GamesListResponse {
        return gameApiService.getGames()
    }

    suspend fun getGameById(id: String): GameResponse {
        return gameApiService.getGameById(id)
    }

    suspend fun getGameByName(name: String): GamesListResponse {
        return gameApiService.getGamesByName(name)
    }

    suspend fun getMechanics(): MechanicsListResponse {
        return gameApiService.getMechanics()
    }

    suspend fun getCategories(): CategoriesListResponse {
        return gameApiService.getCategories()
    }

}