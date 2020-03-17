package com.example.neowsapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class AsteroidBrowseResponse(
    @SerializedName("near_earth_objects")
    val nearEarthObjects: ArrayList<Asteroid>
)