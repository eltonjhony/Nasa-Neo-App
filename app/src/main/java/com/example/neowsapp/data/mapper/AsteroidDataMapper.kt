package com.example.neowsapp.data.mapper

import com.example.neowsapp.data.remote.model.Asteroid
import com.example.neowsapp.data.remote.model.CloseApproachData
import com.example.neowsapp.domain.AsteroidMetadata

object AsteroidDataMapper: DataMapper<Asteroid, AsteroidMetadata>() {

    override fun transform(target: Asteroid): AsteroidMetadata {
        return AsteroidMetadata(
            id = target.id,
            name = getName(
                target.name
            ),
            closeAbsoluteDate = getCloseApproachDate(
                target.closeApproachData
            ),
            absoluteMagnitude = "${target.absoluteMagnitudeH} au",
            estimatedDiameter = "${target.estimatedDiameter.kilometers.estimatedDiameterMax} km",
            relativeVelocity = getRelativeVelocity(
                target.closeApproachData
            ),
            distanceFromEarth = getDistanceFromEarth(
                target.closeApproachData
            ),
            isPotentiallyHazardousAsteroid = target.isPotentiallyHazardousAsteroid
        )
    }

    override fun transform(target: List<Asteroid>): List<AsteroidMetadata> = target.map {
        transform(
            it
        )
    }

    private fun getName(name: String): String {
        return if (name.isBlank()) {
            "N/A"
        } else {
            name
        }
    }

    private fun getCloseApproachDate(list: ArrayList<CloseApproachData>): String {
        return if (list.isEmpty()) {
            "N/A"
        } else {
            list.first().closeApproachDate
        }
    }

    private fun getRelativeVelocity(list: ArrayList<CloseApproachData>): String {
        return if (list.isEmpty()) {
            "N/A"
        } else {
            "${list.first().relativeVelocity.kilometersPerSecond} km/s"
        }
    }

    private fun getDistanceFromEarth(list: ArrayList<CloseApproachData>): String {
        return if (list.isEmpty()) {
            "N/A"
        } else {
            "${list.first().missDistance.astronomical} au"
        }
    }

}