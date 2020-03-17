package com.example.neowsapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asteroids")
class AsteroidEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val closeAbsoluteDate: String,
    val absoluteMagnitude: String,
    val estimatedDiameter: String,
    val relativeVelocity: String,
    val distanceFromEarth: String,
    val isPotentiallyHazardousAsteroid: Boolean,
    var imageOfDayUrl: String?
)