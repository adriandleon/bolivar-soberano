package com.japsystem.bolivarsoberano

import java.lang.Math.abs
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * Created by: Adrian De León
 * Date: 21 Aug 2018
 * email: adriandleon@gmail.com
 */
class FormatCurrency {

    private val unitsMap = arrayOf(
            "", "un", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve",
            "diez", "once", "doce", "trece", "catorce", "quince", "dieciseis", "diecisiete", "dieciocho", "diecinueve",
            "veinte", "veintiún", "veintidos", "veintitres", "veinticuatro", "veinticinco", "veintiséis", "veintisiete", "veintiocho", "veintinueve")
    private val tensMap = arrayOf("", "diez", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa")
    private val centsMap = arrayOf("", "ciento", "doscientos", "trescientos", "cuatrocientos", "quinientos", "seiscientos", "setecientos", "ochocientos", "novecientos")

    internal fun formatToCurrency(number: BigDecimal, currencySymbol: String?): String {

        val formatter = NumberFormat.getCurrencyInstance(Locale("es", "VE")) as DecimalFormat
        val symbols = formatter.decimalFormatSymbols
        symbols.currencySymbol = currencySymbol ?: ""
        symbols.monetaryDecimalSeparator = ','
        formatter.decimalFormatSymbols = symbols

        return formatter.format(number).replace("  ", " ")
    }

    internal fun toSovereigns(strong: Double) : BigDecimal {
        return BigDecimal(strong / 100_000)
    }

    internal fun toStrong(sovereigns: Double) : BigDecimal {
        return BigDecimal(sovereigns * 100_000)
    }

    private fun convertNumberToText(inputNumber: Long): String {

        var number = inputNumber

        if (number == 0L)
            return ""

        if (number < 0)
            return "menos " + formatToText(abs(number))

        var words = ""

        if (number / 1_000_000_000_000_000_000L > 0) {
            words += formatToText(number / 1_000_000_000_000_000_000L)

            words += if (number / 1_000_000_000_000_000_000L == 1L) {
                if (number % 1_000_000_000_000_000_000L != 0L) {
                    " trillón "
                } else {
                    " trillón de "
                }
            } else {
                if (number % 1_000_000_000_000_000_000L != 0L) {
                    " trillones "
                } else {
                    " trillones de "
                }
            }

            number %= 1_000_000_000_000_000_000L
        }

        if (number / 1_000_000_000_000L > 0) {
            words += formatToText(number / 1_000_000_000_000L)

            words += if (number / 1_000_000_000_000L == 1L) {
                if (number % 1_000_000_000_000L != 0L) {
                    " billón "
                } else {
                    " billón de "
                }
            } else {
                if (number % 1_000_000_000_000L != 0L) {
                    " billones "
                } else {
                    " billones de "
                }
            }

            number %= 1_000_000_000_000L
        }

        if (number / 1_000_000 > 0) {
            words += formatToText(number / 1_000_000)

            words += if (number / 1_000_000 == 1L) {
                if (number % 1_000_000 != 0L) {
                    " millón "
                } else {
                    " millón de "
                }
            } else {
                if (number % 1_000_000 != 0L) {
                    " millones "
                } else {
                    " millones de "
                }
            }

            number %= 1_000_000
        }

        if (number / 1_000 > 0) {

            words += if (number / 1_000 == 1L) {
                "mil "
            } else {
                formatToText(number / 1_000) + " mil "
            }

            number %= 1_000
        }

        if (number < 1_000) {

            words += if (number == 100L) {
                "cien "
            } else {
                centsMap[number.toInt() / 100] + " "
            }

            number %= 100
        }

        if (number < 100) {
            if (number < 30)
                words += unitsMap[number.toInt()]
            else {
                words += tensMap[number.toInt() / 10]
                if (number % 10 > 0)
                    words += " y " + unitsMap[number.toInt() % 10]
            }
        }

        return words
    }

    internal fun formatToText(inputNumber: Long): String {
        return convertNumberToText(inputNumber).trim()
                .replace("   ", " ").replace("  ", " ")
    }
}