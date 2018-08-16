package com.japsystem.bolivarsoberano

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.math.RoundingMode


class MainActivity : AppCompatActivity() {

    lateinit var mAdView : AdView
    var strongToSovereigns : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this, getString(R.string.admob_id))

        inputText.addTextChangedListener(mTextWatcher)
        labelTextView.text = getString(R.string.label_exchange,
                getString(R.string.strong_name_plural), getString(R.string.sovereign_name_plural))
        switchCurrency.setOnCheckedChangeListener { _, checked ->

            strongToSovereigns = checked
            inputText.text.clear()

            if (strongToSovereigns) {
                resultAmount.text = getString(R.string.sovereigns_hint)
                labelTextView.text = getString(R.string.label_exchange,
                        getString(R.string.strong_name_plural), getString(R.string.sovereign_name_plural))
            } else {
                resultAmount.text = getString(R.string.strong_hint)
                labelTextView.text = getString(R.string.label_exchange,
                        getString(R.string.sovereign_name_plural), getString(R.string.strong_name_plural))
            }
        }

        // AdMob
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    private val mTextWatcher = object : TextWatcher {

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable) {
            val decimal: Double

            if (!s.isEmpty()) {
                decimal = if (strongToSovereigns) {
                    toSovereigns(inputText.currencyDouble)
                } else {
                    toStrong(inputText.currencyDouble)
                }

                printResult(decimal)
            }
        }
    }

    private fun toSovereigns(strong: Double) : Double {
        return strong / 100000
    }

    private fun toStrong(sovereigns: Double) : Double {
        return sovereigns * 100000
    }

    private fun printResult(result: Double) {

        if (strongToSovereigns) {
            resultAmount.text = FormatCurrency.formatToCurrency(result, getString(R.string.sovereigns_symbol) + " ")
        } else {
            resultAmount.text = FormatCurrency.formatToCurrency(result, getString(R.string.strong_symbol) + " ")
        }

        val decimals = 2
        val value = BigDecimal(result).setScale(decimals, RoundingMode.HALF_EVEN)
        val integerValue = value.abs().toBigInteger()
        val decimalValue = value.subtract(BigDecimal(integerValue)).multiply(BigDecimal(10).pow(decimals)).toBigInteger()

        val integerPart = ConvertToText.convertirLetras(integerValue.toInt())
        val decimalPart = ConvertToText.convertirLetras(decimalValue.toInt())

        var textResult = ""
        val currencyNamePlural = if (strongToSovereigns) {
            getString(R.string.sovereign_name_plural).toLowerCase()
        } else {
            getString(R.string.strong_name_plural).toLowerCase()
        }
        val currencyNameSingular = if (strongToSovereigns) {
            getString(R.string.sovereign_name_singular).toLowerCase()
        } else {
            getString(R.string.strong_name_singular).toLowerCase()
        }

        if (integerPart == "") {

            if (decimalPart != "") {
                textResult = if (decimalPart == "un") {
                    "Son: ${decimalPart.trim()} céntimo de $currencyNameSingular."
                } else {
                    "Son: ${decimalPart.trim()} céntimos de $currencyNameSingular."
                }
            }

        } else {

            textResult = if (integerPart == "un") {
                "Son: ${integerPart.trim()} $currencyNameSingular"
            } else {
                "Son: ${integerPart.trim()} $currencyNamePlural"
            }

            textResult += if (decimalPart != "") {
                if (decimalPart == "un") {
                    " con ${decimalPart.trim()} céntimo."
                } else {
                    " con ${decimalPart.trim()} céntimos."
                }
            } else {
                "."
            }
        }

        resultText.text = textResult
    }
}
