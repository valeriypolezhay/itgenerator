package com.example.itgenerator.screens.home

import android.app.Application

import androidx.lifecycle.ViewModel
import com.example.itgenerator.database.PositionDatabaseDao
import com.example.itgenerator.getGreatPositionName
import kotlinx.coroutines.*


class HomeViewModel(val database: PositionDatabaseDao, application: Application) : ViewModel() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var custom2: String = ""

    fun generate(): String {
        initialize()

        return custom2
    }

    private fun initialize() {
        uiScope.launch {
            custom2 = getAllAsList()
        }
    }

    private suspend fun getAllAsList(): String {
        return withContext(Dispatchers.IO) {

            val items = database.getAllPositionsAsList()

            val customList = ArrayList<String>()

            items.forEach {
                customList.add(it.positionName)
            }

            getGreatPositionName(customList)


        }
    }


}