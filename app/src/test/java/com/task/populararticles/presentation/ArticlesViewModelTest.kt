package com.task.populararticles.presentation

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.task.populararticles.data.FakeRepository
import com.task.populararticles.data.mappers.SharedMapper
import com.task.populararticles.domain.usecase.PopularRemoteUseCase
import com.task.populararticles.presentation.di.providers.ResourceProvider
import com.task.populararticles.presentation.ui.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class ArticlesViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()


    @get:Rule
    var mainCoroutineRule = mainCoroutineRule()

    private lateinit var viewModel: HomeViewModel

    @Mock
    private lateinit var contextMock: Context

    @Before
    fun setup() {
        viewModel = HomeViewModel(
            ResourceProvider(contextMock),
            PopularRemoteUseCase(SharedMapper(), FakeRepository())
        )
    }


    @Test
    fun `Get most viewed popular article with, returns success`() {
        viewModel.getMostPopular("7")
        val value = viewModel.popularArticles.getOrAwaitValueTest()

        assertThat(value.articleData?.first()?.section).isEqualTo(
            "U.S."
        )
    }
}