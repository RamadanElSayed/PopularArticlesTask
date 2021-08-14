package com.task.populararticles.presentation.ui.interfaces

import com.task.populararticles.domain.model.ArticleData

interface OnRecyclerItemClickListener {
    fun onRecyclerItemClickListener(articleData: ArticleData)
}