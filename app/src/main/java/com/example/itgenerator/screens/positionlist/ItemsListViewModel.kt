package com.example.itgenerator.screens.positionlist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.itgenerator.database.Position
import com.example.itgenerator.database.PositionDatabaseDao
import kotlinx.coroutines.*

class ItemsListViewModel
    (val database: PositionDatabaseDao, application: Application) : AndroidViewModel(application) {

    /**
     * viewModelJob allows us to cancel all coroutines started by this ViewModel.
     */
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

//    val allItems = MutableLiveData<List<Position?>>()

    private var items = database.getAllPositions()

    init {
        print(items)
        initialize()
    }

    private fun initialize() {
        uiScope.launch {
            items = getAllItemsFromDb()
        }
    }

    private suspend fun getAllItemsFromDb(): LiveData<List<Position>> {
        return withContext(Dispatchers.IO) {
            val item = database.getAllPositions()

            item
        }
    }

}