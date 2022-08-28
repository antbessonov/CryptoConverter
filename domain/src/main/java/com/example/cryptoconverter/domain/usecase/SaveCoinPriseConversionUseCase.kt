package com.example.cryptoconverter.domain.usecase

import com.example.cryptoconverter.domain.model.CoinInfo
import com.example.cryptoconverter.domain.repository.CoinRepository

class SaveCoinPriseConversionUseCase(
    private val repository: CoinRepository,
) {

    operator fun invoke(
        coinInfo: CoinInfo,
        amount: Double,
    ) = repository.saveCoinPriseConversion(coinInfo, amount)
}