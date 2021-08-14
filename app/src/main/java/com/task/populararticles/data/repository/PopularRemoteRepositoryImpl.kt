package com.task.populararticles.data.repository
import android.content.Context
import com.task.populararticles.data.model.PopularArticlesBaseResponse
import com.task.populararticles.data.remote.ApiClient
import com.task.populararticles.domain.repository.PopularRemoteRepository
import com.task.populararticles.presentation.di.providers.ResourceProvider
import javax.inject.Inject

class PopularRemoteRepositoryImpl @Inject constructor(
    private val apiClient: ApiClient) : PopularRemoteRepository {

    override suspend fun getMostPopular(period: String?): PopularArticlesBaseResponse =
        apiClient.getMostPopularData(period)

}