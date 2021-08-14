package com.task.populararticles.data.mappers

import com.task.populararticles.data.model.PopularArticlesBaseResponse
import com.task.populararticles.domain.model.ArticleData
import com.task.populararticles.domain.model.PopularArticleBaseData
import javax.inject.Inject

class SharedMapper @Inject constructor() {
    fun toPopularArticleBaseData(popularArticlesBaseResponse: PopularArticlesBaseResponse): PopularArticleBaseData {
        val newArticlesData = popularArticlesBaseResponse.results?.map {
            ArticleData(
                it.publishedDate,
                it.section,
                it.source,
                it.title,
                it.type,
                it.abstract,
                it.nytdsection
            )
        }


        return PopularArticleBaseData(
            popularArticlesBaseResponse.copyright,
            popularArticlesBaseResponse.numResults,
            newArticlesData,
            popularArticlesBaseResponse.status
        )
    }

}