package com.example.itgenerator.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PositionDatabaseDao {

    @Insert
    fun insert(item: Position)

    @Update
    fun update(item: Position)

    @Query("SELECT * FROM positions_list")
    fun getAllPositions(): LiveData<List<Position>>

    @Query("SELECT * FROM positions_list")
    fun getAllPositionsAsList(): List<Position>

    @Query("DELETE FROM positions_list")
    fun clear()

    @Query("SELECT * from positions_list WHERE positionID = :key")
    fun get(key: Long): Position?

}