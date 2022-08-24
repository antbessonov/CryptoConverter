package com.example.cryptoconverter.domain

class GetCoinPriceConversionUseCase(
    private val repository: CoinRepository,
) {

    suspend operator fun invoke(
        amount: Int,
        id: Int,
        currencyConvertID: Int,
    ) = repository.getCoinPriceConversion(amount, id, currencyConvertID)
}