package com.example.cryptoconverter.domain.usecase

import com.example.cryptoconverter.domain.repository.CoinRepository

class GetCoinInfoListUseCase(
    private val repository: CoinRepository,
) {

    operator fun invoke() = repository.getCoinInfoList()
}