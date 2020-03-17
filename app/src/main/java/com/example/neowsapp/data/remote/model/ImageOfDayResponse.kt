package com.example.neowsapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class ImageOfDayResponse(
    val title: String,
    @SerializedName("media_type")
    val mediaType: String,
    val url: String
) {
    private fun isImageType(): Boolean = "image" == mediaType
    fun getImageUrl(): String? = if (isImageType()) { url } else { null }
}