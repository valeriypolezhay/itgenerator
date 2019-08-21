package com.example.itgenerator.screens.additem

import android.app.Application
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.itgenerator.database.Position
import com.example.itgenerator.database.PositionDatabaseDao
import kotlinx.coroutines.*

class AddItemViewModel(val database: PositionDatabaseDao, application: Application) : ViewModel() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

//    val allItems = database.getAllPositions()

    fun onAddItem(item: Position) {
        uiScope.launch {
            insert(item)
        }
    }

    private suspend fun insert(item: Position) {
        withContext(Dispatchers.IO) {
            database.insert(item)
        }
    }

}