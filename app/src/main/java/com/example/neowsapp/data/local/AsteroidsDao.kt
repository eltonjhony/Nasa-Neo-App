package com.example.neowsapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.neowsapp.data.local.entity.AsteroidEntity

@Dao
interface AsteroidsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(asteroid: AsteroidEntity)

    @Query("SELECT * FROM asteroids" )
    fun getAllAsteroids() : List<AsteroidEntity>
}