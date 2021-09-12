package com.task.populararticles.presentation.ui.home

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.populararticles.data.error.ErrorHandling
import com.task.populararticles.data.resources.DataState
import com.task.populararticles.domain.model.ArticleData
import com.task.populararticles.domain.model.PopularArticleBaseData
import com.task.populararticles.domain.usecase.PopularRemoteUseCase
import com.task.populararticles.presentation.di.providers.ResourceProvider
import com.task.populararticles.presentation.ui.adapter.ArticleAdapter
import com.task.populararticles.presentation.ui.interfaces.OnRecyclerItemClickListener
import com.task.populararticles.utile.PERIOD_VALUE
import com.task.populararticles.utile.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider, private val useCase: PopularRemoteUseCase
) : ViewModel() {

    @Inject
    lateinit var adapter: ArticleAdapter
    val articleDetails = SingleLiveEvent<ArticleData>()
    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading
    val failure: MutableLiveData<Boolean> = MutableLiveData()
    val isShowRefresh: MutableLiveData<Boolean> = MutableLiveData()
    var periodValue: ObservableField<String> = ObservableField("")
    var errorMessage: ObservableField<String> = ObservableField("")
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    private val _popularArticles = MutableLiveData<PopularArticleBaseData>()
    val popularArticles: LiveData<PopularArticleBaseData> = _popularArticles

    fun oncreate() {
        _loading.value = false
        failure.value = false
        isShowRefresh.value = false
        periodValue.set(PERIOD_VALUE)
        adapter.addListener(object : OnRecyclerItemClickListener {
            override fun onRecyclerItemClickListener(articleData: ArticleData) {
                articleDetails.value = articleData
            }

        })
        getMostPopular(periodValue.get())
    }

    fun getMostPopular(period: String?) {
        isShowRefresh.value = false
        coroutineScope.launch {
            useCase.getMostPopular(period).collect { it ->
                when (it) {
                    is DataState.Loading -> {
                        _loading.postValue(true)
                    }

                    is DataState.Success -> {
                        withContext(Dispatchers.Main)
                        {
                            _popularArticles.value=it.data!!
                            _loading.postValue(false)
                            failure.postValue(false)
                            it.data.articleData?.let { it1 -> adapter.addData(it1) }
                        }
                    }

                    is DataState.Error -> {
                        errorMessage.set(it.error?.let { it1 -> ErrorHandling(it1, resourceProvider).errorMsg })
                        _loading.postValue(false)
                        failure.postValue(true)
                    }
                }

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}