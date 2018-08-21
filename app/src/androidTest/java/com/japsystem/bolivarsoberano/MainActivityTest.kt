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

        val values: List<Array<String>> = arrayOf(
                arrayOf("", "Bs.S 0,00", ""),
                arrayOf("-5209887", "Bs.S -0,52", "Menos cincuenta y dos céntimos de bolívar soberano."),
                arrayOf("0", "Bs.S 0,00", ""),
                arrayOf("11", "Bs.S 0,00", ""),
                arrayOf("3437773000", "Bs.S 343,78", "Trescientos cuarenta y tres bolívares soberanos con setenta y ocho céntimos."),
                arrayOf("4512312400", "Bs.S 451,23", "Cuatrocientos cincuenta y un bolívares soberanos con veintitres céntimos."),
                arrayOf("32344282100", "Bs.S 3.234,43", "Tres mil doscientos treinta y cuatro bolívares soberanos con cuarenta y tres céntimos."),
                arrayOf("22365712100", "Bs.S 2.236,57", "Dos mil doscientos treinta y seis bolívares soberanos con cincuenta y siete céntimos."),
                arrayOf("123456", "Bs.S 0,01", "Un céntimo de bolívar soberano."),
                arrayOf("458998", "Bs.S 0,05", "Cinco céntimos de bolívar soberano."),
                arrayOf("5487984", "Bs.S 0,55", "Cincuenta y cinco céntimos de bolívar soberano."),
                arrayOf("84151287", "Bs.S 8,42", "Ocho bolívares soberanos con cuarenta y dos céntimos."),
                arrayOf("330000000", "Bs.S 33,00", "Treinta y tres bolívares soberanos."),
                arrayOf("261404512", "Bs.S 26,14", "Veintiséis bolívares soberanos con catorce céntimos."),
                arrayOf("6751726561", "Bs.S 675,17", "Seiscientos setenta y cinco bolívares soberanos con diecisiete céntimos."),
                arrayOf("56480584087", "Bs.S 5.648,06", "Cinco mil seiscientos cuarenta y ocho bolívares soberanos con seis céntimos."),
                arrayOf("487115408795", "Bs.S 48.711,54", "Cuarenta y ocho mil setecientos once bolívares soberanos con cincuenta y cuatro céntimos."),
                arrayOf("5320875105408", "Bs.S 532.087,51", "Quinientos treinta y dos mil ochenta y siete bolívares soberanos con cincuenta y un céntimos."),
                arrayOf("91919191919191", "Bs.S 9.191.919,19", "Nueve millones ciento noventa y un mil novecientos diecinueve bolívares soberanos con diecinueve céntimos."),
                arrayOf("651987654879435", "Bs.S 65.198.765,49", "Sesenta y cinco millones ciento noventa y ocho mil setecientos sesenta y cinco bolívares soberanos con cuarenta y nueve céntimos.")
        ).toList()

        for (value in values) {
            onView(ViewMatchers.withId(R.id.inputText))
                    .perform(ViewActions.replaceText(value[0]), ViewActions.closeSoftKeyboard())

            onView(ViewMatchers.withId(R.id.resultAmount))
                    .check(ViewAssertions.matches(ViewMatchers.withText(value[1])))

            onView(ViewMatchers.withId(R.id.resultText))
                    .check(ViewAssertions.matches(ViewMatchers.withText(value[2])))
        }
    }

    @Test
    fun sovereignsToStrong() {

        val values: List<Array<String>> = arrayOf(
                arrayOf("", "Bs.F 0,00", ""),

                arrayOf("0", "Bs.F 0,00", ""),
                arrayOf("1", "Bs.F 1.000,00", "Mil bolívares fuertes."),
                arrayOf("29", "Bs.F 29.000,00", "Veintinueve mil bolívares fuertes."),
                arrayOf("108", "Bs.F 108.000,00", "Ciento ocho mil bolívares fuertes."),
                arrayOf("3300", "Bs.F 3.300.000,00", "Tres millones trescientos mil bolívares fuertes."),
                arrayOf("1455", "Bs.F 1.455.000,00", "Un millón cuatrocientos cincuenta y cinco mil bolívares fuertes."),
                arrayOf("2548", "Bs.F 2.548.000,00", "Dos millones quinientos cuarenta y ocho mil bolívares fuertes."),
                arrayOf("8489", "Bs.F 8.489.000,00", "Ocho millones cuatrocientos ochenta y nueve mil bolívares fuertes."),
                arrayOf("48651", "Bs.F 48.651.000,00", "Cuarenta y ocho millones seiscientos cincuenta y un mil bolívares fuertes."),
                arrayOf("100000", "Bs.F 100.000.000,00", "Cien millones de bolívares fuertes."),
                arrayOf("5487698", "Bs.F 5.487.698.000,00", "Cinco mil cuatrocientos ochenta y siete millones seiscientos noventa y ocho mil bolívares fuertes."),
                arrayOf("68451408", "Bs.F 68.451.408.000,00", "Sesenta y ocho mil cuatrocientos cincuenta y un millones cuatrocientos ocho mil bolívares fuertes."),
                arrayOf("710848415", "Bs.F 710.848.415.000,00", "Setecientos diez mil ochocientos cuarenta y ocho millones cuatrocientos quince mil bolívares fuertes."),
                arrayOf("9875404841", "Bs.F 9.875.404.841.000,00", "Nueve billones ochocientos setenta y cinco mil cuatrocientos cuatro millones ochocientos cuarenta y un mil bolívares fuertes."),
                arrayOf("32970480754", "Bs.F 32.970.480.754.000,00", "Treinta y dos billones novecientos setenta mil cuatrocientos ochenta millones setecientos cincuenta y cuatro mil bolívares fuertes."),
                arrayOf("201887645871", "Bs.F 201.887.645.871.000,00", "Doscientos un billones ochocientos ochenta y siete mil seiscientos cuarenta y cinco millones ochocientos setenta y un mil bolívares fuertes."),
                arrayOf("9874651876544", "Bs.F 9.874.651.876.544.000,00", "Nueve mil ochocientos setenta y cuatro billones seiscientos cincuenta y un mil ochocientos setenta y seis millones quinientos cuarenta y cuatro mil bolívares fuertes.")

        ).toList()

        onView((ViewMatchers.withId(R.id.switchCurrency))).perform(ViewActions.click())

        for (value in values) {
            onView(ViewMatchers.withId(R.id.inputText)).perform(ViewActions.clearText())
                    .perform(ViewActions.replaceText(value[0]), ViewActions.closeSoftKeyboard())

            onView(ViewMatchers.withId(R.id.resultAmount))
                    .check(ViewAssertions.matches(ViewMatchers.withText(value[1])))

            onView(ViewMatchers.withId(R.id.resultText))
                    .check(ViewAssertions.matches(ViewMatchers.withText(value[2])))
        }
    }
}