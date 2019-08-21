package com.example.itgenerator.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "positions_list")
data class Position(
    @PrimaryKey(autoGenerate = true)
    var positionID: Long = 0L,

    @ColumnInfo(name = "position_name")
    var positionName: String = "undefined"

)