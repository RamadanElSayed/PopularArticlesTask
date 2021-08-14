package com.task.populararticles.domain.model


import com.google.gson.annotations.SerializedName

data class PopularArticleBaseData(
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("num_results")
    val numResults: Int?,
    @SerializedName("results")
    val articleData: List<ArticleData>?,
    @SerializedName("status")
    val status: String?

)