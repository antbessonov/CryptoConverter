package com.example.cryptoconverter.domain.usecase

import com.example.cryptoconverter.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinPriseConversionUseCase @Inject constructor(
    private val repository: CoinRepository,
) {

    operator fun invoke() = repository.getCoinPriseConversion()
}