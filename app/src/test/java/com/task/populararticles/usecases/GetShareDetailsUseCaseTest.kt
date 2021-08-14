package com.task.populararticles.usecases

import com.task.populararticles.data.repository.PopularRemoteRepositoryImpl
import com.task.populararticles.data.resources.DataState
import com.task.populararticles.domain.model.ArticleData
import com.task.populararticles.domain.model.PopularArticleBaseData

class GetShareDetailsUseCaseTest {

    private lateinit var RemoteRepoImpl: PopularRemoteRepositoryImpl
    private lateinit var mostPopularData: PopularArticleBaseData

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }


    suspend fun getMostPopular(period: String): DataState<PopularArticleBaseData> {
        return if (shouldReturnNetworkError) {
            DataState.Error(null)

        } else {
            mostPopularData = PopularArticleBaseData("", 0, listOf(), "")

            DataState.Success(mostPopularData)
        }
    }

}