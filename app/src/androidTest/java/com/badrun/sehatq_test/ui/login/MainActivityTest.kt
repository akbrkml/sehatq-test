package com.badrun.sehatq_test.ui.login


import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.badrun.sehatq_test.MainActivity
import com.badrun.sehatq_test.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun searchProductAndGoToDetail() {
        onView(withId(R.id.search_view)).perform(click())

        onView(withId(R.id.et_search)).perform(replaceText("ni"), closeSoftKeyboard())

        Thread.sleep(3000)

        onView(withId(R.id.rv_search)).perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        onView(withId(R.id.btn_buy)).check(matches(isDisplayed()))

        pressBack()
    }

    @Test
    fun clickProductListAndGoToDetail() {
        onView(withId(R.id.search_view)).check(matches(isDisplayed()))

        Thread.sleep(3000)

        onView(withId(R.id.rv_main)).perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        onView(withId(R.id.btn_buy)).check(matches(isDisplayed()))

        pressBack()
    }

    @Test
    fun addProductToPurchasedAndGoToDetail() {
        onView(withId(R.id.search_view)).check(matches(isDisplayed()))

        Thread.sleep(3000)

        onView(withId(R.id.rv_main)).perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        onView(withId(R.id.btn_buy)).check(matches(isDisplayed())).perform(click())

        pressBack()

        onView(withId(R.id.historyFragment)).perform(click())

        onView(withId(R.id.rv_history)).perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        pressBack()
    }

}
