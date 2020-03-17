package com.example.neowsapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neowsapp.R
import com.example.neowsapp.infrastructure.extensions.hide
import com.example.neowsapp.infrastructure.extensions.load
import com.example.neowsapp.ui.details.DetailsActivity
import com.example.neowsapp.ui.main.adapters.AsteroidsAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private val asteroidsAdapter: AsteroidsAdapter by lazy { AsteroidsAdapter { DetailsActivity.callingIntent(this, it) }}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureUi()
        initObservers()
    }

    private fun configureUi() {
        asteroidsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = asteroidsAdapter
        }
    }

    private fun initObservers() {
        viewModel.getAsteroidView().observe(this, Observer {
            imageOfDayView.load(it.imageOfDayUrl)
            if (it.imageOfDayUrl == null) imageOfDayText.hide()
            asteroidsAdapter.setAsteroids(asteroids = it.metadata.toMutableList())
        })
    }

}
