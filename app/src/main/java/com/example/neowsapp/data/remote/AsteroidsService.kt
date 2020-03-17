package com.example.neowsapp.data.remote

import com.example.neowsapp.data.remote.model.AsteroidFeedResponse
import com.example.neowsapp.data.remote.model.AsteroidBrowseResponse
import com.example.neowsapp.data.remote.model.ImageOfDayResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidsService {

    @GET("neo/rest/v1/neo/browse")
    fun getAsteroids(@Query("page") page: Int = 0,
                     @Query("size") size: Int = 20,
                     @Query("api_key") apiKey: String = "DEMO_KEY"): Single<AsteroidBrowseResponse>

    @GET("neo/rest/v1/feed")
    fun getAsteroidsByRange(@Query("start_date") startDate: String,
                            @Query("end_date") endDate: String,
                            @Query(value = "detailed") detailed: Boolean = true,
                            @Query("api_key") apiKey: String = "DEMO_KEY"): Single<AsteroidFeedResponse>

    @GET("planetary/apod")
    fun getImageOfDay(@Query("api_key") apiKey: String = "DEMO_KEY"): Single<ImageOfDayResponse>
}
