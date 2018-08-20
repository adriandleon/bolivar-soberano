package com.japsystem.bolivarsoberano

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class FormatCurrencyTest {

    private lateinit var obj: FormatCurrency

    private val sovereignsSymbol = "Bs.S "
    private val strongSymbol = "Bs.F "

    @Before
    fun init() {
        obj = FormatCurrency()
    }

    @Test
    fun formatToCurrencyTest() {

        assertEquals(obj.formatToCurrency(BigDecimal(.0154698), sovereignsSymbol), "Bs.S 0,02")
        assertEquals(obj.formatToCurrency(BigDecimal(3_300_000), sovereignsSymbol), "Bs.S 3.300.000,00")
        assertEquals(obj.formatToCurrency(BigDecimal(98_465_198.32454), sovereignsSymbol), "Bs.S 98.465.198,32")
        assertEquals(obj.formatToCurrency(BigDecimal(1), sovereignsSymbol), "Bs.S 1,00")
        assertEquals(obj.formatToCurrency(BigDecimal(10_101.01), sovereignsSymbol), "Bs.S 10.101,01")
        assertEquals(obj.formatToCurrency(BigDecimal(-3_546_847_987_987.3164987), sovereignsSymbol), "Bs.S -3.546.847.987.987,32")
        assertEquals(obj.formatToCurrency(BigDecimal(.69842098), strongSymbol), "Bs.F 0,70")
        assertEquals(obj.formatToCurrency(BigDecimal(1_000_000), strongSymbol), "Bs.F 1.000.000,00")
        assertEquals(obj.formatToCurrency(BigDecimal(90_909_090.909090), strongSymbol), "Bs.F 90.909.090,91")
        assertEquals(obj.formatToCurrency(BigDecimal(-4), strongSymbol), "Bs.F -4,00")
        assertEquals(obj.formatToCurrency(BigDecimal(0.00000), strongSymbol), "Bs.F 0,00")
    }

    @Test
    fun toSovereignsTest() {

        assertEquals(obj.formatToCurrency(obj.toSovereigns(65_498.651), sovereignsSymbol), "Bs.S 0,65")
        assertEquals(obj.formatToCurrency(obj.toSovereigns(3_300_000.00), sovereignsSymbol), "Bs.S 33,00")
        assertEquals(obj.formatToCurrency(obj.toSovereigns(.0), sovereignsSymbol), "Bs.S 0,00")
        assertEquals(obj.formatToCurrency(obj.toSovereigns(67_517_265.61), sovereignsSymbol), "Bs.S 675,17")
        assertEquals(obj.formatToCurrency(obj.toSovereigns(14_888_888.0), sovereignsSymbol), "Bs.S 148,89")
        assertEquals(obj.formatToCurrency(obj.toSovereigns(-15_000.0), sovereignsSymbol), "Bs.S -0,15")
    }

    @Test
    fun toStrongTest() {

        assertEquals(obj.formatToCurrency(obj.toStrong(6_598.15), strongSymbol), "Bs.F 659.815.000,00")
        assertEquals(obj.formatToCurrency(obj.toStrong(3_300_000.00), strongSymbol), "Bs.F 330.000.000.000,00")
        assertEquals(obj.formatToCurrency(obj.toStrong(.0), strongSymbol), "Bs.F 0,00")
        assertEquals(obj.formatToCurrency(obj.toStrong(629.0), strongSymbol), "Bs.F 62.900.000,00")
        assertEquals(obj.formatToCurrency(obj.toStrong(148.89), strongSymbol), "Bs.F 14.889.000,00")
        assertEquals(obj.formatToCurrency(obj.toStrong(-7.77), strongSymbol), "Bs.F -777.000,00")
    }
}