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

    @Test
    fun formatToTextTest() {
        assertEquals(obj.formatToText(-8777L), "menos ocho mil setecientos setenta y siete")
        assertEquals(obj.formatToText(-199L), "menos ciento noventa y nueve")
        assertEquals(obj.formatToText(-11L), "menos once")
        assertEquals(obj.formatToText(-3L), "menos tres")
        assertEquals(obj.formatToText(5L), "cinco")
        assertEquals(obj.formatToText(64L), "sesenta y cuatro")
        assertEquals(obj.formatToText(526L), "quinientos veintiséis")
        assertEquals(obj.formatToText(7_892L), "siete mil ochocientos noventa y dos")
        assertEquals(obj.formatToText(52_365L), "cincuenta y dos mil trescientos sesenta y cinco")
        assertEquals(obj.formatToText(452_635L), "cuatrocientos cincuenta y dos mil seiscientos treinta y cinco")
        assertEquals(obj.formatToText(2_525_634L), "dos millones quinientos veinticinco mil seiscientos treinta y cuatro")
        assertEquals(obj.formatToText(25_236_635L), "veinticinco millones doscientos treinta y seis mil seiscientos treinta y cinco")
        assertEquals(obj.formatToText(263_263_878L), "doscientos sesenta y tres millones doscientos sesenta y tres mil ochocientos setenta y ocho")
        assertEquals(obj.formatToText(2_103_634_625L), "dos mil ciento tres millones seiscientos treinta y cuatro mil seiscientos veinticinco")
        assertEquals(obj.formatToText(45_800_644_000L), "cuarenta y cinco mil ochocientos millones seiscientos cuarenta y cuatro mil")
        assertEquals(obj.formatToText(987_532_100_876L), "novecientos ochenta y siete mil quinientos treinta y dos millones cien mil ochocientos setenta y seis")
        assertEquals(obj.formatToText(4_180_858_625_055L), "cuatro billones ciento ochenta mil ochocientos cincuenta y ocho millones seiscientos veinticinco mil cincuenta y cinco")
        assertEquals(obj.formatToText(19_789_184_087_654L), "diecinueve billones setecientos ochenta y nueve mil ciento ochenta y cuatro millones ochenta y siete mil seiscientos cincuenta y cuatro")
        assertEquals(obj.formatToText(841_541_874_700_014L), "ochocientos cuarenta y un billones quinientos cuarenta y un mil ochocientos setenta y cuatro millones setecientos mil catorce")
        assertEquals(obj.formatToText(3_840_345_774_870_150L), "tres mil ochocientos cuarenta billones trescientos cuarenta y cinco mil setecientos setenta y cuatro millones ochocientos setenta mil ciento cincuenta")
        assertEquals(obj.formatToText(29_112_000_774_856_456L), "veintinueve mil ciento doce billones setecientos setenta y cuatro millones ochocientos cincuenta y seis mil cuatrocientos cincuenta y seis")
        assertEquals(obj.formatToText(391_000_733_554_999_000L), "trescientos noventa y un mil billones setecientos treinta y tres mil quinientos cincuenta y cuatro millones novecientos noventa y nueve mil")
        assertEquals(obj.formatToText(5_782_010_720_011_349_813L), "cinco trillones setecientos ochenta y dos mil diez billones setecientos veinte mil once millones trescientos cuarenta y nueve mil ochocientos trece")
    }
}