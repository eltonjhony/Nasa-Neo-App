package com.example.neowsapp.domain

import java.io.Serializable

data class AsteroidView(
    val imageOfDayUrl: String?,
    val metadata: List<AsteroidMetadata>
): Serializable