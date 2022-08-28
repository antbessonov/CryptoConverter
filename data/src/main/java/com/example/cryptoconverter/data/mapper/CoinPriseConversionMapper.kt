package com.example.cryptoconverter.data.mapper

import com.example.cryptoconverter.data.sharedprefs.model.CoinPriseConversionSharedPrefs
import com.example.cryptoconverter.domain.model.CoinInfo
import com.example.cryptoconverter.domain.model.CoinPriceConversion

class CoinPriseConversionMapper {

    companion object {
        private const val UNIT_CURRENCY = 1.0
    }

    fun mapSharedPrefsToEntity(
        sharedPrefs: CoinPriseConversionSharedPrefs,
    ) = CoinPriceConversion(priceConversion = sharedPrefs.priceConversion)

    fun mapEntityToSharedPrefs(
        coinPriceConversion: CoinPriceConversion,
    ) = CoinPriseConversionSharedPrefs(
        priceConversion = coinPriceConversion.priceConversion
    )

    fun priceConversion(coinInfo: CoinInfo, amount: Double): Double? {
        return coinInfo.price?.let {
            UNIT_CURRENCY.div(it).times(amount)
        }
    }
}