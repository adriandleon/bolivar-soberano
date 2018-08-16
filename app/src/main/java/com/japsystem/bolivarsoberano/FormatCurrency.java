package com.japsystem.bolivarsoberano;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import static java.lang.StrictMath.abs;

public class FormatCurrency {

    private static final String TAG = "FormatCurrency";

    static String formatToCurrency(Double number, String currencySymbol) {

        DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setCurrencySymbol(currencySymbol != null ? currencySymbol : "");
        symbols.setMonetaryDecimalSeparator(',');
        symbols.setPerMill('.');
        symbols.setDigit('.');
        symbols.setPatternSeparator('.');
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(symbols);

        return formatter.format(number);
    }

    private static String unitsMapEnglish[] = { "zero", "one", "two", "three", "four", "five","six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
    private static String tensMapEnglish[] = { "zero", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };

    private static String unitsMapSpanish[] = { "", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve", "diez", "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho", "diecinueve" };
    private static String tensMapSpanish[] = { "", "diez", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa" };
    private static String millsMapSpanish[] = { "", "cien", "doscientos", "trescientos", "cuatrocientos", "quinientos", "seiscientos", "setecientos", "ochocientos", "novecientos" };

    static String formatToTextSpanish(int number) {
        if (number == 0)
            return "";

        if (number < 0)
            return "menos " + formatToTextEnglish(abs(number));

        String words = "";

        if ((number / 1000000000) > 0)
        {
            String billones = formatToTextSpanish(number / 1000000000);

            if (billones.equals("uno")) {
                words += billones + " billón ";
            } else {
                words += billones + " billones ";
            }

            number %= 1000000000;
        }

        if ((number / 1000000) > 0)
        {
            words += formatToTextSpanish(number / 1000000) + " million ";
            number %= 1000000;
        }

        if ((number / 1000) > 0)
        {
            words += formatToTextSpanish(number / 1000) + " thousand ";
            number %= 1000;
        }

        if ((number / 100) > 0)
        {
            words += formatToTextSpanish(number / 100) + " hundred ";
            number %= 100;
        }

        if (number > 0)
        {
            if (number < 20)
                words += unitsMapSpanish[number];
            else
            {
                words += tensMapSpanish[number / 10];
                if ((number % 10) > 0)
                    words += " y " + unitsMapSpanish[number % 10];
            }
        }

        return words;
    }

    static String formatToTextEnglish(int number) {
        if (number == 0)
            return "zero";

        if (number < 0)
            return "minus " + formatToTextEnglish(abs(number));

        String words = "";

        if ((number / 1000000000) > 0)
        {
            words += formatToTextEnglish(number / 1000000000) + " billion ";
            number %= 1000000000;
        }

        if ((number / 1000000) > 0)
        {
            words += formatToTextEnglish(number / 1000000) + " million ";
            number %= 1000000;
        }

        if ((number / 1000) > 0)
        {
            words += formatToTextEnglish(number / 1000) + " thousand ";
            number %= 1000;
        }

        if ((number / 100) > 0)
        {
            words += formatToTextEnglish(number / 100) + " hundred ";
            number %= 100;
        }

        if (number > 0)
        {
            if (number < 20)
                words += unitsMapEnglish[number];
            else
            {
                words += tensMapEnglish[number / 10];
                if ((number % 10) > 0)
                    words += "-" + unitsMapEnglish[number % 10];
            }
        }

        return words;
    }
}