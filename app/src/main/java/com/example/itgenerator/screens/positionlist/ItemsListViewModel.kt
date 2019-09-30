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

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private lateinit var items :LiveData<List<Position>> //= database.getAllPositions()

    init {
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


    fun clearDB(){
        uiScope.launch {
            clear()
        }
    }

    private suspend fun clear(){
        return withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    fun deleteItem(itemToDelete:String){
        uiScope.launch {
            deleteByNumber(itemToDelete)
        }
    }

    private suspend fun deleteByNumber(itemToDelete:String){
        return withContext(Dispatchers.IO) {
            database.deleteByPosition(itemToDelete)
        }
    }

}