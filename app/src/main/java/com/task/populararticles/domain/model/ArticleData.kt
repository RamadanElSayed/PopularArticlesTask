package com.task.populararticles.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArticleData(
    @SerializedName("published_date")
    val publishedDate: String?=null,
    @SerializedName("section")
    val section: String?=null,
    @SerializedName("source")
    val source: String?=null,
    @SerializedName("title")
    val title: String?=null,
    @SerializedName("type")
    val type: String?=null,
    @SerializedName("abstract")
    val `abstract`: String?=null,
    @SerializedName("nytdsection")
    val nytdsection: String?=null

) : Serializable