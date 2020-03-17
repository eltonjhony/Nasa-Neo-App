package com.example.neowsapp.data.remote.model

data class AsteroidFeedResponse(
    val nearEarthObjects: NearEarthObjects
)

data class NearEarthObjects(
    val data: MutableList<Asteroid>
)