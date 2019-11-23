package com.example.square_shaped_triangle.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AppDao {

    @Query("select * from user")
    @Transaction
    fun getUsers(): LiveData<List<User>>

   /* @Query("select * from user u where u.id = :id")
    @Transaction
    fun getFavoriteGames(id: String): LiveData<List<Game>>

    @Query("select * from user u where u.id = :id")
    @Transaction
    fun getOwnedGames(id: String): LiveData<List<Game>>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Transaction
    fun insertUser(user: User)

    @Query("select * from game")
    fun getGames(): LiveData<List<Game>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(game: Game)

    @Query("select * from event")
    fun getEvent(): LiveData<List<Event>>

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    @Transaction
    fun insertOwnedGame(list: List<Game>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteGame(list: List<Game>)

    @Query("select * from event")
    fun getEvent(): LiveData<List<Event>>

    @Query("select * from event ev where ev.id = :eventId")
    @Transaction
    fun getPlayers(eventId: String): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Transaction
    fun insertPlayers(players: Players)

    @Query("select * from event  ev where ev.id = :eventId")
    @Transaction
    fun getEventGames(eventId: String): LiveData<List<Game>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvent(event: Event)*/

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