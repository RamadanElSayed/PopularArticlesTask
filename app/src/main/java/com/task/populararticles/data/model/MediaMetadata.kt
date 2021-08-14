package com.task.populararticles.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MediaMetadata(
    @SerializedName("format")
    val format: String?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("width")
    val width: Int?
): Serializable
