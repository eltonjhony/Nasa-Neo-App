package com.example.neowsapp.data.mapper

import com.example.neowsapp.data.local.entity.AsteroidEntity
import com.example.neowsapp.domain.AsteroidMetadata

object AsteroidViewDataMapper: DataMapper<AsteroidEntity, AsteroidMetadata>() {

    override fun transform(target: AsteroidEntity): AsteroidMetadata {
        return AsteroidMetadata(
            id = target.id,
            name = target.name,
            closeAbsoluteDate = target.closeAbsoluteDate,
            absoluteMagnitude = target.absoluteMagnitude,
            relativeVelocity = target.relativeVelocity,
            estimatedDiameter = target.estimatedDiameter,
            distanceFromEarth = target.distanceFromEarth,
            isPotentiallyHazardousAsteroid = target.isPotentiallyHazardousAsteroid)
    }

    override fun transform(target: List<AsteroidEntity>): List<AsteroidMetadata> = target.map { transform(it) }
}