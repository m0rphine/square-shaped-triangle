package com.example.square_shaped_triangle.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AppDao {

    @Query("select * from user")
    @Transaction
    fun getUsers(): LiveData<List<User>>

    @Query("select * from user u where u.id = :id")
    @Transaction
    fun getFavoriteGames(id: String): LiveData<FavoriteGames>

    @Query("select * from user u where u.id = :id")
    @Transaction
    fun getOwnedGames(id: String): LiveData<OwnedGames>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("select * from game")
    fun getGames(): LiveData<List<Game>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(game: Game)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteGame(favoriteGames: FavoriteGames)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOwnedGame(ownedGames: OwnedGames)

    @Query("select * from event")
    fun getEvent(): LiveData<List<Event>>

    @Query("select * from event ev where ev.id = :eventId")
    fun getPlayers(eventId: String): LiveData<Players>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayers(players: Players)

    @Query("select * from event")
    fun getEventGames(): LiveData<List<EventGames>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvent(event: Event)

}

@Database(entities = [User::class, Game::class, Event::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: AppDao
}

private lateinit var INSTANCE: AppDatabase

fun getDatabase(context: Context): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "appdatabase"
            ).build()
        }
    }
    return INSTANCE
}