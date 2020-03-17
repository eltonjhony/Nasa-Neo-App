package com.example.neowsapp

import android.app.Application
import android.content.Context
import com.example.neowsapp.infrastructure.NeoWorkManager

class NeoApplication: Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        NeoWorkManager.init()
        context = this.applicationContext
    }
}