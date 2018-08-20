package com.japsystem.bolivarsoberano

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule @JvmField
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setUp() {
    }

    @Test
    fun testAddButtonText() {
        val v = withId(R.id.help_info_button)
        onView(v).check(matches(isDisplayed()))
    }

    @Test
    fun enterAndReset() {
        onView(ViewMatchers.withId(R.id.inputText))
                .perform(ViewActions.replaceText("123456"), ViewActions.closeSoftKeyboard())

        onView((ViewMatchers.withId(R.id.switchCurrency))).perform(ViewActions.click())

        onView(ViewMatchers.withId(R.id.resultAmount))
                .check(ViewAssertions.matches(ViewMatchers.withText("Bs.F 0,00")))

        onView(ViewMatchers.withId(R.id.resultText))
                .check(ViewAssertions.matches(ViewMatchers.withText("")))
    }

    @Test
    fun strongToSovereigns() {
        onView(ViewMatchers.withId(R.id.inputText))
                .perform(ViewActions.replaceText("123456"), ViewActions.closeSoftKeyboard())

        onView(ViewMatchers.withId(R.id.resultAmount))
                .check(ViewAssertions.matches(ViewMatchers.withText("Bs.S 0,01")))

        onView(ViewMatchers.withId(R.id.resultText))
                .check(ViewAssertions.matches(ViewMatchers.withText("Un céntimo de bolívar soberano.")))
    }

    @Test
    fun sovereignsToStrong() {
        onView((ViewMatchers.withId(R.id.switchCurrency))).perform(ViewActions.click())

        // TODO: Put all test inside a loop

        // Test #1: Bs.S 33,00 -> Bs.F 3.300.000,00

        onView(ViewMatchers.withId(R.id.inputText))
                .perform(ViewActions.replaceText("3300"), ViewActions.closeSoftKeyboard())

        onView(ViewMatchers.withId(R.id.resultAmount))
                .check(ViewAssertions.matches(ViewMatchers.withText("Bs.F 3.300.000,00")))

        onView(ViewMatchers.withId(R.id.resultText))
                .check(ViewAssertions.matches(ViewMatchers.withText("Tres millones trescientos mil bolívares fuertes.")))

        // Test #2: Bs.S 0,01 -> Bs.F 100.000,00

        onView(ViewMatchers.withId(R.id.inputText)).perform(ViewActions.clearText())
                .perform(ViewActions.replaceText("1"), ViewActions.closeSoftKeyboard())

        onView(ViewMatchers.withId(R.id.resultAmount))
                .check(ViewAssertions.matches(ViewMatchers.withText("Bs.F 1.000,00")))

        onView(ViewMatchers.withId(R.id.resultText))
                .check(ViewAssertions.matches(ViewMatchers.withText("Mil bolívares fuertes.")))

        // Test #3: Bs.S 14,55 -> Bs.F 1.455.000,00

        onView(ViewMatchers.withId(R.id.inputText)).perform(ViewActions.clearText())
                .perform(ViewActions.replaceText("1455"), ViewActions.closeSoftKeyboard())

        onView(ViewMatchers.withId(R.id.resultAmount))
                .check(ViewAssertions.matches(ViewMatchers.withText("Bs.F 1.455.000,00")))

        onView(ViewMatchers.withId(R.id.resultText))
                .check(ViewAssertions.matches(ViewMatchers.withText("Un millón cuatrocientos cincuenta y cinco mil bolívares fuertes.")))
    }
}