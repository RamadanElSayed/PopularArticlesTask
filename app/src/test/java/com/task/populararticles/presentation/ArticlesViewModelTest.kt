package com.task.populararticles.presentation
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.task.populararticles.data.FakeRepository
import com.task.populararticles.data.mappers.SharedMapper
import com.task.populararticles.domain.model.PopularArticleBaseData
import com.task.populararticles.domain.usecase.PopularRemoteUseCase
import com.task.populararticles.presentation.di.providers.ResourceProvider
import com.task.populararticles.presentation.ui.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.nullValue
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.ArgumentMatchers.anyInt

import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.*

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
       // contextMock= ApplicationProvider.getApplicationContext()
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

        // Create observer - no need for it to do anything!
        val observer = Observer<PopularArticleBaseData> {}
        try {

            // Observe the LiveData forever
            viewModel.popularArticles.observeForever(observer)

            // When adding a new task
         //   tasksViewModel.addNewTask()

            // Then the new task event is triggered
          //  val value =  viewModel.popularArticles.value
            val value = viewModel.popularArticles.getOrAwaitValueTest()


          //  `when`(value?.articleData?.first()?.section).thenReturn(fileOutputStream)
        //    verify(viewModel, times(1)).popularArticles.getOrAwaitValueTest()

            assertThat(value?.articleData?.first()?.section).isEqualTo(
                "U.S."
            )
            //assertThat(value.articleData?.first()?.section,`is` ("U.S.") )
           // assertThat(value.getContentIfNotHandled(), not(nullValue()))

        } finally {
            // Whatever happens, don't forget to remove the observer!
            viewModel.popularArticles.removeObserver(observer)
        }


    }
}