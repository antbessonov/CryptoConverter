package com.example.cryptoconverter.data.sharedprefs

import com.example.cryptoconverter.data.sharedprefs.model.CoinPriseConversionSharedPrefs

interface CoinPriseConversionStorage {

    fun save(coinPriseConversionSharedPrefs: CoinPriseConversionSharedPrefs)

    fun get(): CoinPriseConversionSharedPrefs
}