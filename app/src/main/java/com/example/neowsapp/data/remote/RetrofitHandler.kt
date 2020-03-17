package com.example.neowsapp.data.remote

import com.example.neowsapp.data.remote.model.Asteroid
import com.example.neowsapp.data.remote.model.AsteroidFeedResponse
import com.example.neowsapp.data.remote.model.NearEarthObjects
import com.google.gson.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class RetrofitHandler {

    companion object {

        private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().registerTypeAdapter(
                        AsteroidFeedResponse::class.java,
                        AsteroidFeedTypeAdapter()
                    ).create()
                )
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        fun provideAsteroidService(): AsteroidsService {
            return retrofit.create(AsteroidsService::class.java)
        }

    }

    class AsteroidFeedTypeAdapter : JsonDeserializer<AsteroidFeedResponse> {

        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): AsteroidFeedResponse {

            val response = AsteroidFeedResponse(NearEarthObjects(mutableListOf()))

            val nearEarthObjects = json?.asJsonObject?.getAsJsonObject("near_earth_objects")
            val keys = nearEarthObjects?.keySet()?.iterator()

            while (keys?.hasNext() == true) {
                val dynamicKey = keys.next()
                nearEarthObjects.get(dynamicKey).asJsonArray.forEach {
                    val asteroid = Gson().fromJson(it.asJsonObject, Asteroid::class.java)
                    response.nearEarthObjects.data.add(asteroid)
                }
            }

            return response
        }

    }

}
