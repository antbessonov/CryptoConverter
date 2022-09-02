package com.example.cryptoconverter.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.cryptoconverter.domain.model.CoinInfo
import com.example.cryptoconverter.domain.repository.CoinRepository
import com.example.cryptoconverter.domain.usecase.GetCoinInfoUseCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetCoinInfoUseCaseTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val repository = mock<CoinRepository>()

    @Test
    fun shouldReturnCorrectData() {

        val idCoin = 1
        val testCoinInfo = CoinInfo(
            id = idCoin,
            name = "Bitcoin",
            symbol = "BTC",
            lastUpdated = "Время последнего обновления: 00:00:00",
            price = 19983.24,
            url = "https://s2.coinmarketcap.com/static/img/coins/64x64/1.png"
        )
        val liveCoinInfo = MutableLiveData<CoinInfo>()
        liveCoinInfo.value = testCoinInfo

        Mockito.`when`(repository.getCoinInfo(idCoin)).thenReturn(liveCoinInfo)

        val getCoinInfoUseCase = GetCoinInfoUseCase(repository = repository)
        val actual = getCoinInfoUseCase(idCoin)

        val expected = CoinInfo(
            id = 1,
            name = "Bitcoin",
            symbol = "BTC",
            lastUpdated = "Время последнего обновления: 00:00:00",
            price = 19983.24,
            url = "https://s2.coinmarketcap.com/static/img/coins/64x64/1.png"
        )

        Assert.assertEquals(expected, actual.value)
    }
}