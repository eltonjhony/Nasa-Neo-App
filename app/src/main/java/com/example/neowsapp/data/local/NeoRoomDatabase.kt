package com.example.neowsapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.neowsapp.data.local.entity.AsteroidEntity

@Database(entities = [AsteroidEntity::class], version = 1, exportSchema = false)
abstract class NeoRoomDatabase : RoomDatabase() {

    abstract fun asteroidsDao() : AsteroidsDao

    companion object {
        fun getDatabase(context: Context): NeoRoomDatabase {
            return Room.databaseBuilder(context.applicationContext,
                NeoRoomDatabase::class.java, "neo.db")
                .build()
        }
    }
}