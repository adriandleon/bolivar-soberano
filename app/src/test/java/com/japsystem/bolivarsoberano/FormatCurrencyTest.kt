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

        assertEquals(obj.formatToCurrency(obj.toSovereigns(-15_000.0), sovereignsSymbol), "Bs.S -0,15")
        assertEquals(obj.formatToCurrency(obj.toSovereigns(.0), sovereignsSymbol), "Bs.S 0,00")
        assertEquals(obj.formatToCurrency(obj.toSovereigns(.56), sovereignsSymbol), "Bs.S 0,00")
        assertEquals(obj.formatToCurrency(obj.toSovereigns(8.74), sovereignsSymbol), "Bs.S 0,00")
        assertEquals(obj.formatToCurrency(obj.toSovereigns(76.50), sovereignsSymbol), "Bs.S 0,00")
        assertEquals(obj.formatToCurrency(obj.toSovereigns(876.77), sovereignsSymbol), "Bs.S 0,01")
        assertEquals(obj.formatToCurrency(obj.toSovereigns(7_541.06), sovereignsSymbol), "Bs.S 0,08")
        assertEquals(obj.formatToCurrency(obj.toSovereigns(65_498.65), sovereignsSymbol), "Bs.S 0,65")
        assertEquals(obj.formatToCurrency(obj.toSovereigns(134_718.32), sovereignsSymbol), "Bs.S 1,35")
        assertEquals(obj.formatToCurrency(obj.toSovereigns(3_300_000.00), sovereignsSymbol), "Bs.S 33,00")
        assertEquals(obj.formatToCurrency(obj.toSovereigns(67_517_265.61), sovereignsSymbol), "Bs.S 675,17")
        assertEquals(obj.formatToCurrency(obj.toSovereigns(614_888_888.00), sovereignsSymbol), "Bs.S 6.148,89")
        assertEquals(obj.formatToCurrency(obj.toSovereigns(9_540_058_000.21), sovereignsSymbol), "Bs.S 95.400,58")
    }

    @Test
    fun toStrongTest() {

        assertEquals(obj.formatToCurrency(obj.toStrong(-7.77), strongSymbol), "Bs.F -777.000,00")
        assertEquals(obj.formatToCurrency(obj.toStrong(.0), strongSymbol), "Bs.F 0,00")
        assertEquals(obj.formatToCurrency(obj.toStrong(.04), strongSymbol), "Bs.F 4.000,00")
        assertEquals(obj.formatToCurrency(obj.toStrong(.19), strongSymbol), "Bs.F 19.000,00")
        assertEquals(obj.formatToCurrency(obj.toStrong(8.42), strongSymbol), "Bs.F 842.000,00")
        assertEquals(obj.formatToCurrency(obj.toStrong(33.44), strongSymbol), "Bs.F 3.344.000,00")
        assertEquals(obj.formatToCurrency(obj.toStrong(629.0), strongSymbol), "Bs.F 62.900.000,00")
        assertEquals(obj.formatToCurrency(obj.toStrong(148.89), strongSymbol), "Bs.F 14.889.000,00")
        assertEquals(obj.formatToCurrency(obj.toStrong(6_598.15), strongSymbol), "Bs.F 659.815.000,00")
        assertEquals(obj.formatToCurrency(obj.toStrong(92_012.36), strongSymbol), "Bs.F 9.201.236.000,00")
        assertEquals(obj.formatToCurrency(obj.toStrong(600_010.99), strongSymbol), "Bs.F 60.001.099.000,00")
        assertEquals(obj.formatToCurrency(obj.toStrong(3_300_000.00), strongSymbol), "Bs.F 330.000.000.000,00")
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

        assertEquals(obj.formatToText(1_000_000L), "un millón de")
        assertEquals(obj.formatToText(1_800_454L), "un millón ochocientos mil cuatrocientos cincuenta y cuatro")
        assertEquals(obj.formatToText(6_000_000L), "seis millones de")
        assertEquals(obj.formatToText(6_800_454L), "seis millones ochocientos mil cuatrocientos cincuenta y cuatro")

        assertEquals(obj.formatToText(1_000_000_000_000L), "un billón de")
        assertEquals(obj.formatToText(1_548_300_000_000L), "un billón quinientos cuarenta y ocho mil trescientos millones de")
        assertEquals(obj.formatToText(7_000_000_000_000L), "siete billones de")
        assertEquals(obj.formatToText(7_930_000_000_000L), "siete billones novecientos treinta mil millones de")

        assertEquals(obj.formatToText(1_000_000_000_000_000_000L), "un trillón de")
        assertEquals(obj.formatToText(1_000_078_000_000_000_000L), "un trillón setenta y ocho billones de")
        assertEquals(obj.formatToText(8_000_000_000_000_000_000L), "ocho trillones de")
        assertEquals(obj.formatToText(8_930_000_000_001_000_000L), "ocho trillones novecientos treinta mil billones un millón de")
    }
}