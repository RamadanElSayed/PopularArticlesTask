package com.task.populararticles.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PopularArticlesBaseResponse(
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("num_results")
    val numResults: Int?,
    @SerializedName("results")
    val results: List<Result>?,
    @SerializedName("status")
    val status: String?
): Serializable
