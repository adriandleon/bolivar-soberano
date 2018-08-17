package com.japsystem.bolivarsoberano;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import static java.lang.StrictMath.abs;

public class FormatCurrency {

    private static final String TAG = "FormatCurrency";

    static String formatToCurrency(BigDecimal number, String currencySymbol) {

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

    private static final String[] unitsMapEnglish = { "zero", "one", "two", "three", "four", "five","six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
    private static final String[] tensMapEnglish = { "zero", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };

    private static String formatToTextEnglish(int number) {
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