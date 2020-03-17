package com.example.neowsapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.neowsapp.data.repository.AsteroidsDataRepository
import com.example.neowsapp.domain.AsteroidView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val asteroidsRepository by lazy { AsteroidsDataRepository() }
    private val asteroidView: MutableLiveData<AsteroidView> = MutableLiveData()

    init { loadAsteroids() }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    fun getAsteroidView(): LiveData<AsteroidView> = asteroidView

    private fun loadAsteroids() {
        compositeDisposable.add(asteroidsRepository.getAsteroids()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result, _ -> onResult(result) })
    }

    private fun onResult(result: AsteroidView?) = result?.let { asteroidView.value = it }
}
