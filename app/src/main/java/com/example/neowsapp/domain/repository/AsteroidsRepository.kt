package com.example.neowsapp.domain.repository

import com.example.neowsapp.data.local.entity.AsteroidEntity
import com.example.neowsapp.domain.AsteroidView
import io.reactivex.Single

interface AsteroidsRepository {
    fun getAsteroids(): Single<AsteroidView>
    fun getAsteroidsByRange(startDate: String, endDate: String): Single<AsteroidView>
    fun persist(asteroidEntities: List<AsteroidEntity>)
}