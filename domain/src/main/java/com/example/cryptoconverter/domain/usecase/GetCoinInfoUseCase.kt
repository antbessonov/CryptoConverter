package com.example.cryptoconverter.domain.usecase

import com.example.cryptoconverter.domain.repository.CoinRepository

class GetCoinInfoUseCase(
    private val repository: CoinRepository,
) {

    operator fun invoke(id: Int) = repository.getCoinInfo(id)
}