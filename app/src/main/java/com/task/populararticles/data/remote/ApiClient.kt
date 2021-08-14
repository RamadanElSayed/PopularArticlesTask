package com.task.populararticles.data.remote

import com.task.populararticles.data.model.PopularArticlesBaseResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {

    @GET(EndPoints.GETMOSTPOBULAR)
    suspend fun getMostPopularData(@Path("period") period: String?): PopularArticlesBaseResponse
}