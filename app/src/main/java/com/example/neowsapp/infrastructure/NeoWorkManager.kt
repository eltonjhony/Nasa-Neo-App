package com.example.neowsapp.infrastructure

import android.content.Context
import android.util.Log
import androidx.work.*
import com.example.neowsapp.data.mapper.AsteroidEntityDataMapper
import com.example.neowsapp.data.repository.AsteroidsDataRepository
import com.example.neowsapp.domain.repository.AsteroidsRepository
import com.example.neowsapp.infrastructure.extensions.addDays
import com.example.neowsapp.infrastructure.extensions.format
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.util.*
import java.util.concurrent.TimeUnit

class NeoWorkManager(context: Context, params: WorkerParameters): Worker(context, params) {

    private val repository: AsteroidsRepository by lazy { AsteroidsDataRepository() }

    companion object {
        private val TAG = NeoWorkManager::class.java.simpleName

        fun init() {

            val workManager = WorkManager.getInstance()

            val constraints: Constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            workManager.cancelAllWorkByTag(TAG)

            val periodicBuilder = PeriodicWorkRequest.Builder(NeoWorkManager::class.java, 24, TimeUnit.HOURS)
            val worker = periodicBuilder.addTag(TAG).setConstraints(constraints).build()
            workManager.enqueueUniquePeriodicWork(TAG, ExistingPeriodicWorkPolicy.KEEP, worker)
        }
    }

    override fun doWork(): Result {
        try {
            val asteroidView = repository.getAsteroidsByRange(Date().format(), Date().addDays(7).format())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .blockingGet()

            val asteroids = asteroidView.metadata.map { AsteroidEntityDataMapper.transform(it) }
            asteroids.forEach { it.imageOfDayUrl = asteroidView.imageOfDayUrl }
            repository.persist(asteroids)
        } catch (e: Exception) {
            Log.d(TAG, "Error performing background operation: ${e.localizedMessage}")
            return Result.failure()
        }
        return Result.success()
    }

}