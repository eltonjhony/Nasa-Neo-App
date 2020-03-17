package com.example.neowsapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class Asteroid(
    val id: Long,
    val name: String,
    @SerializedName("absolute_magnitude_h")
    val absoluteMagnitudeH: String,
    @SerializedName("close_approach_data")
    val closeApproachData: ArrayList<CloseApproachData>,
    @SerializedName("estimated_diameter")
    val estimatedDiameter: EstimatedDiameter,
    @SerializedName(value = "is_potentially_hazardous_asteroid")
    val isPotentiallyHazardousAsteroid: Boolean,
    var imageOfDayUrl: String? = null
)

data class CloseApproachData(
    @SerializedName("close_approach_date")
    val closeApproachDate: String,
    @SerializedName("relative_velocity")
    val relativeVelocity: RelativeVelocity,
    @SerializedName("miss_distance")
    val missDistance: MissDistance
)

data class RelativeVelocity(
    @SerializedName("kilometers_per_second")
    val kilometersPerSecond: String
)

data class MissDistance(
    val astronomical: String
)

data class EstimatedDiameter(
    val kilometers: Kilometers
)

data class Kilometers(
    @SerializedName("estimated_diameter_min")
    val estimatedDiameterMin: String,
    @SerializedName("estimated_diameter_max")
    val estimatedDiameterMax: String
)