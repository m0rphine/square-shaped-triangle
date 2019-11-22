package com.example.square_shaped_triangle.repository

import androidx.lifecycle.LiveData
import com.example.square_shaped_triangle.database.AppDatabase
import com.example.square_shaped_triangle.database.Event
import com.example.square_shaped_triangle.database.User

class Repository(private val database: AppDatabase) {

    val users: LiveData<List<User>> = database.dao.getUsers()

    val events: LiveData<List<Event>> = database.dao.getEvent()

}