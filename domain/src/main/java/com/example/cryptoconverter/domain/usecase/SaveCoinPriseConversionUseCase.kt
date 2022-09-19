package com.example.cryptoconverter.domain.usecase

import com.example.cryptoconverter.domain.model.CoinInfo
import com.example.cryptoconverter.domain.repository.CoinRepository
import javax.inject.Inject

class SaveCoinPriseConversionUseCase @Inject constructor(
    private val repository: CoinRepository,
) {

    operator fun invoke(
        coinInfo: CoinInfo,
        amount: Double,
    ) = repository.saveCoinPriseConversion(coinInfo, amount)
}