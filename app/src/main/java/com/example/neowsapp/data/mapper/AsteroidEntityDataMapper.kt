package com.example.neowsapp.data.mapper

import com.example.neowsapp.data.local.entity.AsteroidEntity
import com.example.neowsapp.domain.AsteroidMetadata

object AsteroidEntityDataMapper: DataMapper<AsteroidMetadata, AsteroidEntity>() {

    override fun transform(target: AsteroidMetadata): AsteroidEntity {
        return AsteroidEntity(
            id = target.id,
            name = target.name,
            closeAbsoluteDate = target.closeAbsoluteDate,
            absoluteMagnitude = target.absoluteMagnitude,
            relativeVelocity = target.relativeVelocity,
            estimatedDiameter = target.estimatedDiameter,
            distanceFromEarth = target.distanceFromEarth,
            isPotentiallyHazardousAsteroid = target.isPotentiallyHazardousAsteroid,
            imageOfDayUrl = null)
    }

    override fun transform(target: List<AsteroidMetadata>): List<AsteroidEntity> = target.map { transform(it) }
}