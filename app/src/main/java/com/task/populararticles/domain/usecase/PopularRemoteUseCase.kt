package com.task.populararticles.domain.usecase

import com.task.populararticles.data.mappers.SharedMapper
import com.task.populararticles.data.resources.DataState
import com.task.populararticles.domain.model.PopularArticleBaseData
import com.task.populararticles.domain.repository.PopularRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class PopularRemoteUseCase @Inject constructor(
    private val sharedMapper: SharedMapper,
    private val popularRemoteRepository: PopularRemoteRepository
) {

    suspend fun getMostPopular(period: String?): Flow<DataState<PopularArticleBaseData>> = flow {
        emit(DataState.Loading)
        try {
            val response = sharedMapper.toPopularArticleBaseData(
                popularRemoteRepository.getMostPopular(period)
            )
            emit(DataState.Success(response))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }

    }
}