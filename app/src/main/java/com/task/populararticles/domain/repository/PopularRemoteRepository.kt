package com.task.populararticles.domain.repository
import com.task.populararticles.data.model.PopularArticlesBaseResponse

interface PopularRemoteRepository {
    suspend fun getMostPopular(period:String?): PopularArticlesBaseResponse
}