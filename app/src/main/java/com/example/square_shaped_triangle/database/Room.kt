package com.example.square_shaped_triangle.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AppDao {

    @Query("select * from game")
    fun getGames(): LiveData<List<Game>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(game: Game)

    @Query("select * from event")
    fun getEvent(): LiveData<List<Event>>

//    @Query("select * from event where id = :id")
//    fun getEventById(id: String): LiveData<Event>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvent(event: Event)

}

@Database(entities = [Game::class, Event::class], version = 1)
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