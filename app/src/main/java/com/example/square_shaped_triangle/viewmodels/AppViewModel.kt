package com.example.square_shaped_triangle.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.square_shaped_triangle.database.getDatabase
import com.example.square_shaped_triangle.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val appRepository = Repository(getDatabase(application))

}