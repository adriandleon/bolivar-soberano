package com.japsystem.bolivarsoberano

import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*


/**
 * Created by: Adrian De León
 * Date: 21 Aug 2018
 * email: adriandleon@gmail.com
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mAdView : AdView
    private var strongToSovereigns : Boolean = true
    private var textToSpeech : TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this, "ca-app-pub-3221550046636898~5903342189") // Production ID
//        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713") // Testing ID

        inputText.addTextChangedListener(mTextWatcher)
        labelTextView.text = getString(R.string.label_exchange,
                getString(R.string.strong_name_plural), getString(R.string.sovereign_name_plural))
        switchCurrency.setOnCheckedChangeListener { _, checked ->

            strongToSovereigns = checked
            inputText.text.clear()
            resultText.text = ""
            textToSpeechButton.visibility = View.INVISIBLE

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

        textToSpeech = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                val ttsLang = textToSpeech?.setLanguage(Locale("spa", "USA"))

                if (ttsLang == TextToSpeech.LANG_MISSING_DATA || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "The Language is not supported!")
                } else {
                    Log.i("TTS", "Language Supported.")
                }
                Log.i("TTS", "Initialization success.")
            } else {
                Toast.makeText(applicationContext, "TTS Initialization failed!", Toast.LENGTH_SHORT).show()
            }
        })

        textToSpeechButton.setOnClickListener { textToSpeech() }
        resultText.setOnClickListener { textToSpeech() }

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
            showAboutDialog()
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

            if (s.isNotEmpty() && s.isNotBlank()) {
                decimal = if (strongToSovereigns) {
                    FormatCurrency().toSovereigns(inputText.currencyDouble)
                } else {
                    FormatCurrency().toStrong(inputText.currencyDouble)
                }

                printResult(decimal)
            }
        }
    }

    private fun printResult(result: BigDecimal) {

        if (strongToSovereigns) {
            resultAmount.text = FormatCurrency().formatToCurrency(result, getString(R.string.sovereigns_symbol) + " ")
        } else {
            resultAmount.text = FormatCurrency().formatToCurrency(result, getString(R.string.strong_symbol) + " ")
        }

        val decimals = 2
        val value = result.setScale(decimals, RoundingMode.HALF_EVEN)
        val integerValue = value.abs().toBigInteger()
        val decimalValue = value.subtract(BigDecimal(integerValue)).multiply(BigDecimal(10).pow(decimals)).toBigInteger()

        val integerPart = FormatCurrency().formatToText(integerValue.toLong())
        val decimalPart = FormatCurrency().formatToText(decimalValue.toLong())

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
                textResult = if (decimalPart.trim() == "un") {
                    "${decimalPart.trim()} céntimo de $currencyNameSingular."
                } else {
                    "${decimalPart.trim()} céntimos de $currencyNameSingular."
                }
            }

        } else {

            textResult = if (integerPart.trim() == "un") {
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

        textToSpeechButton.visibility = if (textResult.isNotEmpty()) View.VISIBLE else View.INVISIBLE
        resultText.text = textResult.capitalize()
    }

    private fun textToSpeech() {
        if (resultText.text.isEmpty()) return

        val data = resultText.text.toString()

        val speechStatus = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech?.speak(data, TextToSpeech.QUEUE_FLUSH, null, "44")
        } else {
            @Suppress("DEPRECATION")
            textToSpeech?.speak(data, TextToSpeech.QUEUE_FLUSH, null)
        }

        if (speechStatus == TextToSpeech.ERROR) {
            Log.e("TTS", "Error in converting Text to Speech!")
        }
    }

    private fun showAboutDialog() {
        var message = "Version " +
                getString(R.string.app_version_name) +
                " (" + getString(R.string.app_version_code) + ")\n"
        message += "©2018 - JAP System C.A." + "\n\n"

        message += getString(R.string.round_info)

        message += "\n\nInfo: invjapsystem@gmail.com"

        AlertDialog.Builder(this).setTitle(getString(R.string.app_name))
                .setMessage(message)
                .setPositiveButton(getString(android.R.string.ok), null)
                .create()
                .show()
    }

    override fun onDestroy() {
        super.onDestroy()

        if (textToSpeech != null) {
            textToSpeech?.stop()
            textToSpeech?.shutdown()
        }
    }
}
