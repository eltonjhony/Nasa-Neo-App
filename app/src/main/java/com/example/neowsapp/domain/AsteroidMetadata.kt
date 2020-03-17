package com.example.neowsapp.domain

import java.io.Serializable

data class AsteroidMetadata(
    val id: Long,
    val name: String,
    val closeAbsoluteDate: String,
    val absoluteMagnitude: String,
    val estimatedDiameter: String,
    val relativeVelocity: String,
    val distanceFromEarth: String,
    val isPotentiallyHazardousAsteroid: Boolean
): Serializable