package com.example.cryptoconverter.domain

class GetCoinInfoUseCase(
    private val repository: CoinRepository,
) {

    operator fun invoke(id: Int) = repository.getCoinInfo(id)
}