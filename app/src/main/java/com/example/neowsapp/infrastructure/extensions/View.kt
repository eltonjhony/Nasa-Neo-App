package com.example.neowsapp.infrastructure.extensions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.hide() {
    visibility = View.GONE
}

fun ImageView.load(url: String?) {
    if (url == null) {
        this.hide()
        return
    }
    Glide.with(this)
        .load(url)
        .into(this)
}