package com.task.populararticles.presentation
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.task.populararticles.R
import com.task.populararticles.domain.model.ArticleData
import com.task.populararticles.domain.model.PopularArticleBaseData
import com.task.populararticles.launchFragmentInHiltContainer
import com.task.populararticles.presentation.ui.articledetails.ArticleDetailsFragment
import com.task.populararticles.presentation.ui.articledetails.ArticleDetailsFragmentArgs
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ArticleDetailsFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    private lateinit var article: ArticleData
    private lateinit var popularArticleBaseData: PopularArticleBaseData

    @Before
    fun setupTest() {
        article = ArticleData(
            "", "section", "source",
            "title", "type", "abstract", ""
        )
        popularArticleBaseData = PopularArticleBaseData(
            "", 0,
            listOf(article), ""
        )
    }

    @Test
    fun articleDetailsFragmentTestDisplayedInUi() {

        // WHEN - Details fragment launched to display task
        val bundle = ArticleDetailsFragmentArgs(article).toBundle()
        launchFragmentInHiltContainer<ArticleDetailsFragment>(bundle, R.style.AppTheme)

        Espresso.onView(withId(R.id.tv_abstract_value))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_publish_value))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_abstract_value))
            .check(ViewAssertions.matches(ViewMatchers.withText("abstract")))
        Espresso.onView(withId(R.id.tv_publish_value))
            .check(ViewAssertions.matches(ViewMatchers.withText("")))

        Thread.sleep(3000)
    }

}