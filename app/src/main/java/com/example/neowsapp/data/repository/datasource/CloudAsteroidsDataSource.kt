package com.example.neowsapp.data.repository.datasource

import com.example.neowsapp.data.remote.model.AsteroidBrowseResponse
import com.example.neowsapp.data.remote.AsteroidsService
import com.example.neowsapp.data.remote.RetrofitHandler
import com.example.neowsapp.data.remote.model.AsteroidFeedResponse
import com.example.neowsapp.data.remote.model.ImageOfDayResponse
import io.reactivex.Single

class CloudAsteroidsDataSource(
    private val asteroidsService: AsteroidsService = RetrofitHandler.provideAsteroidService()) {

    fun getImageOfDay(): Single<ImageOfDayResponse> = asteroidsService.getImageOfDay()
    fun getAsteroids(): Single<AsteroidBrowseResponse> = asteroidsService.getAsteroids()

    fun getAsteroidsByRange(startDate: String, endDate: String): Single<AsteroidFeedResponse> {
        return asteroidsService.getAsteroidsByRange(startDate, endDate)
    }
}