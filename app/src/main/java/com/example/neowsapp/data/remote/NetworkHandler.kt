package com.example.neowsapp.data.remote

import android.content.Context
import com.example.neowsapp.NeoApplication
import com.example.neowsapp.infrastructure.extensions.networkInfo

class NetworkHandler(private val context: Context = NeoApplication.context) {
    val isConnected get() = context.networkInfo?.isConnected ?: false
}