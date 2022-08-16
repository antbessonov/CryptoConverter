package com.example.cryptoconverter.domain

class GetCoinInfoListUseCase(
    private val repository: CoinRepository,
) {

    operator fun invoke() = repository.getCoinInfoList()
}