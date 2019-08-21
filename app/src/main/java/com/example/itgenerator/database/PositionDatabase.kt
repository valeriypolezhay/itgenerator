package com.example.itgenerator.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Position::class], version = 1, exportSchema = false)
abstract class PositionDatabase : RoomDatabase() {

    abstract val positionDatabaseDao: PositionDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: PositionDatabase? = null

        fun getInstance(context: Context): PositionDatabase {

            synchronized(this) {
                var instance = INSTANCE
                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PositionDatabase::class.java,
                        "position_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}