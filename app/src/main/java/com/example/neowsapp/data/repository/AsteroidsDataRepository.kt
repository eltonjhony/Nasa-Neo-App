package com.example.neowsapp.data.repository

import com.example.neowsapp.data.local.entity.AsteroidEntity
import com.example.neowsapp.data.mapper.AsteroidDataMapper
import com.example.neowsapp.data.mapper.AsteroidViewDataMapper
import com.example.neowsapp.data.remote.NetworkHandler
import com.example.neowsapp.data.remote.model.Asteroid
import com.example.neowsapp.data.remote.model.AsteroidBrowseResponse
import com.example.neowsapp.data.remote.model.AsteroidFeedResponse
import com.example.neowsapp.data.remote.model.ImageOfDayResponse
import com.example.neowsapp.data.repository.datasource.CloudAsteroidsDataSource
import com.example.neowsapp.data.repository.datasource.LocalAsteroidsDataSource
import com.example.neowsapp.domain.AsteroidView
import com.example.neowsapp.domain.repository.AsteroidsRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class AsteroidsDataRepository(
    private val cloudDataSource: CloudAsteroidsDataSource = CloudAsteroidsDataSource(),
    private val localDataSource: LocalAsteroidsDataSource = LocalAsteroidsDataSource(),
    private val networkHandler: NetworkHandler = NetworkHandler()
) : AsteroidsRepository {

    override fun getAsteroids(): Single<AsteroidView> {
        return when {
            networkHandler.isConnected -> Single.zip(
                cloudDataSource.getAsteroids(),
                cloudDataSource.getImageOfDay(),
                mergeBrowseRequests()
            )
            else -> localDataSource.getAsteroids().map {
                AsteroidView(
                    imageOfDayUrl = obtainImageOfDayFrom(it),
                    metadata = AsteroidViewDataMapper.transform(it)
                )
            }
        }
    }

    override fun getAsteroidsByRange(
        startDate: String,
        endDate: String
    ): Single<AsteroidView> {
        return Single.zip(
            cloudDataSource.getAsteroidsByRange(startDate, endDate),
            cloudDataSource.getImageOfDay(),
            mergeFeedRequests()
        )
    }

    override fun persist(asteroidEntities: List<AsteroidEntity>) =
        asteroidEntities.forEach { localDataSource.persist(it) }

    private fun mergeBrowseRequests(): BiFunction<AsteroidBrowseResponse, ImageOfDayResponse, AsteroidView> {
        return BiFunction { browseResponse, imageResponse ->
            convert(
                imageResponse,
                browseResponse.nearEarthObjects
            )
        }
    }

    private fun mergeFeedRequests(): BiFunction<AsteroidFeedResponse, ImageOfDayResponse, AsteroidView> {
        return BiFunction { feedResponse, imageResponse ->
            convert(
                imageResponse,
                feedResponse.nearEarthObjects.data
            )
        }
    }

    private fun convert(
        imageResponse: ImageOfDayResponse,
        asteroids: List<Asteroid>
    ): AsteroidView = AsteroidView(
        imageOfDayUrl = imageResponse.getImageUrl(),
        metadata = AsteroidDataMapper.transform(asteroids)
    )

    private fun obtainImageOfDayFrom(entities: List<AsteroidEntity>): String? =
        if (entities.isNotEmpty()) {
            entities.first().imageOfDayUrl
        } else {
            null
        }

}