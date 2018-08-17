package com.japsystem.bolivarsoberano

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.math.RoundingMode


class MainActivity : AppCompatActivity() {

    private lateinit var mAdView : AdView
    private var strongToSovereigns : Boolean = true

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
            resultText.text = ""

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

        root_layout.viewTreeObserver.addOnGlobalLayoutListener {
            val heightDiff = root_layout.rootView.height - root_layout.height

            if (heightDiff > dpToPx(200.0F)) { // if more than 200 dp, it's probably a keyboard...
                // Soft keyboard shows
                logo_jap.visibility = View.GONE
            } else {
                // Soft keyboard hides
                logo_jap.visibility = View.VISIBLE
            }
        }

        help_info_button.setOnClickListener {
            AboutDialog(this).show()
        }

        // AdMob
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    private fun dpToPx(valueInDp: Float): Float {
        val metrics = resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics)
    }

    private val mTextWatcher = object : TextWatcher {

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable) {
            val decimal: BigDecimal

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

    private fun toSovereigns(strong: Double) : BigDecimal {
        return BigDecimal(strong / 100000)
    }

    private fun toStrong(sovereigns: Double) : BigDecimal {
        return BigDecimal(sovereigns * 100000)
    }

    private fun printResult(result: BigDecimal) {

        if (strongToSovereigns) {
            resultAmount.text = FormatCurrency.formatToCurrency(result, getString(R.string.sovereigns_symbol) + " ")
        } else {
            resultAmount.text = FormatCurrency.formatToCurrency(result, getString(R.string.strong_symbol) + " ")
        }

        val decimals = 2
        val value = result.setScale(decimals, RoundingMode.HALF_EVEN)
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
                    "${decimalPart.trim()} céntimo de $currencyNameSingular."
                } else {
                    "${decimalPart.trim()} céntimos de $currencyNameSingular."
                }
            }

        } else {

            textResult = if (integerPart == "un") {
                "${integerPart.trim()} $currencyNameSingular"
            } else {
                "${integerPart.trim()} $currencyNamePlural"
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

        resultText.text = textResult.capitalize()
    }
}
