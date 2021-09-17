package com.task.populararticles.presentation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.task.populararticles.R
import com.task.populararticles.domain.model.ArticleData
import com.task.populararticles.launchFragmentInHiltContainer
import com.task.populararticles.presentation.ui.home.HomeFragment
import com.task.populararticles.presentation.ui.home.HomeFragmentDirections
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.verify

@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var mainCoroutineRule = mainCoroutineRule()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    /*

    What you want to test and your testing strategy determine the kinds of test you are going
    to implement for your app. Unit tests are focused and fast.
     Integration tests verify interaction between parts of your program.
     End-to-end tests verify features, have the highest fidelity, are often instrumented,
     and may take longer to run.


    ViewMatchers - allows to find view in the current view hierarchy

    ViewActions - allows to perform actions on the views

    ViewAssertions - allows to assert state of a view

    The case construct for Espresso tests is the following:

    Base Espresso Test
    onView(ViewMatcher)
     .perform(ViewAction)
       .check(ViewAssertion);
    - Finds the view
    - Performs an action on the view
    - Validates a assertioin
     */
    @Test
    fun articleList_DisplayedInUi() {
        val scenario = launchFragmentInHiltContainer<HomeFragment>(Bundle(), R.style.AppTheme)
        Espresso.onView(ViewMatchers.withId(R.id.shimmerFrameLayout))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.error_message_id))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)
    }

    @Test
    fun clickArticleNavigateToDetailFragmentOne() {
        //  mainCoroutineRule.runBlockingTest
        runBlocking {
            val navController = Mockito.mock(NavController::class.java)
            launchFragmentInHiltContainer<HomeFragment>(Bundle(), R.style.AppTheme) {
                Navigation.setViewNavController(requireView(), navController)
            }


            // WHEN - Click on the first list item
            Espresso.onView(withId(R.id.recyclerView))
                .perform(
                    RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                        ViewMatchers.hasDescendant(ViewMatchers.withText("section")),
                        ViewActions.click()
                    )
                )


            /*
             // WHEN - Click on the "+" button
        onView(withId(R.id.add_task_fab)).perform(click())

        // THEN - Verify that we navigate to the add screen
        verify(navController).navigate(
            TasksFragmentDirections.actionTasksFragmentToAddEditTaskFragment(
                null, getApplicationContext<Context>().getString(R.string.add_task)
            )
        )
             */
            val article = ArticleData("", "section", "source", "title", "type", "abstract", "")

            verify(navController).navigate(
                HomeFragmentDirections.actionHomeFragmentToArticleDetailsFragment(article)
            )

            delay(2000)
        }
        /*
         // GIVEN - On the home screen
        val scenario = launchFragmentInContainer<TasksFragment>(Bundle(), R.style.AppTheme)
        val navController = mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        // WHEN - Click on the "+" button
        onView(withId(R.id.add_task_fab)).perform(click())

        // THEN - Verify that we navigate to the add screen
        verify(navController).navigate(
            TasksFragmentDirections.actionTasksFragmentToAddEditTaskFragment(
                null, getApplicationContext<Context>().getString(R.string.add_task)
            )
        )
         */
    }


    /*
    Similar to runBlockingTest, runBlocking is used here because refreshTasks is a suspend function.

runBlocking vs. runBlockingTest

Both runBlocking and runBlockingTest block the current thread and wait until any associated coroutines launched in the lambda complete.

In addition, runBlockingTest has the following behaviors meant for testing:

It skips delay, so your tests run faster.
It adds testing related assertions to the end of the coroutine. These assertions fail if you launch a coroutine and it continues running after the end of the runBlocking lambda (which is a possible coroutine leak) or if you have an uncaught exception.
It gives you timing control over the coroutine execution.
So why use runBlocking in your test doubles, like FakeTestRepository? Sometimes you will need a coroutine for a test double, in which case you do need to block the current thread. This is, so that when your test doubles are used in a test case, the thread blocks and allows the coroutine to finish before the test does. Test doubles, though, aren't actually defining a test case, so they don't need and shouldn't use all of the test specific features of runBlockingTest.

In summary:

Tests require deterministic behavior so they aren't flaky.
"Normal" coroutines are non-deterministic because they run code asynchronously.
kotlinx-coroutines-test is the gradle dependency for runBlockingTest.
Writing test classes, meaning classes with @Test functions, use runBlockingTest to get deterministic behavior.
Writing test doubles, use runBlocking.
     */
}