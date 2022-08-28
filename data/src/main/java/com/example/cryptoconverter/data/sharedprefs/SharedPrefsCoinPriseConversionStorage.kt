package com.example.cryptoconverter.data.sharedprefs

import android.content.Context
import com.example.cryptoconverter.data.sharedprefs.model.CoinPriseConversionSharedPrefs

class SharedPrefsCoinPriseConversionStorage(
    context: Context,
) : CoinPriseConversionStorage {

    companion object {

        private const val SHARED_PREFS_PRICE_CONVERSION = "shared_prefs_price_conversion"
        private const val KEY_PRICE_CONVERSION = "price_conversion"

        private const val EMPTY_PRICE_CONVERSION = 0.0
    }

    private val sharedPreferences = context.getSharedPreferences(
        SHARED_PREFS_PRICE_CONVERSION,
        Context.MODE_PRIVATE
    )

    override fun save(coinPriseConversionSharedPrefs: CoinPriseConversionSharedPrefs) {
        val priseConversion = coinPriseConversionSharedPrefs
            .priceConversion ?: EMPTY_PRICE_CONVERSION
        sharedPreferences.edit().putLong(
            KEY_PRICE_CONVERSION,
            java.lang.Double.doubleToRawLongBits(priseConversion)
        ).apply()
    }

    override fun get(): CoinPriseConversionSharedPrefs {
        val priseConversion = java.lang.Double.longBitsToDouble(
            sharedPreferences.getLong(
                KEY_PRICE_CONVERSION,
                java.lang.Double.doubleToRawLongBits(EMPTY_PRICE_CONVERSION)
            )
        )
        return CoinPriseConversionSharedPrefs(priseConversion)
    }
}