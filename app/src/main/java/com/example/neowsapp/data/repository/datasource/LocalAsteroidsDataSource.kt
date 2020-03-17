package com.example.neowsapp.data.repository.datasource

import com.example.neowsapp.NeoApplication
import com.example.neowsapp.data.local.AsteroidsDao
import com.example.neowsapp.data.local.NeoRoomDatabase
import com.example.neowsapp.data.local.entity.AsteroidEntity
import io.reactivex.Single

class LocalAsteroidsDataSource(
    private val asteroidsDao: AsteroidsDao = NeoRoomDatabase.getDatabase(NeoApplication.context).asteroidsDao()) {

    fun persist(asteroid: AsteroidEntity) = asteroidsDao.insert(asteroid)

    fun getAsteroids(): Single<List<AsteroidEntity>> {
        return Single.create { emitter ->
            emitter.onSuccess(asteroidsDao.getAllAsteroids())
        }
    }
}