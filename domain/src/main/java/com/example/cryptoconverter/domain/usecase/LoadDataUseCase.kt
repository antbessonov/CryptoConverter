package com.example.cryptoconverter.domain.usecase

import com.example.cryptoconverter.domain.repository.CoinRepository

class LoadDataUseCase(
    private val repository: CoinRepository,
) {

    suspend operator fun invoke() = repository.loadData()
}