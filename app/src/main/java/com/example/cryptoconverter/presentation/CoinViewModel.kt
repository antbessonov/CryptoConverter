package com.example.cryptoconverter.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoconverter.data.repository.CoinRepositoryImpl
import com.example.cryptoconverter.domain.model.CoinInfo
import com.example.cryptoconverter.domain.usecase.*
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)
    private val saveCoinPriseConversionUseCase = SaveCoinPriseConversionUseCase(repository)
    private val getCoinPriseConversionUseCase = GetCoinPriseConversionUseCase(repository)

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }

    val coinInfoList = getCoinInfoListUseCase()
    val coinPriseConversion = getCoinPriseConversionUseCase()

    fun getCoinInfo(id: Int) = getCoinInfoUseCase(id)

    fun saveCoinPriseConversion(
        coinInfo: CoinInfo,
        amount: Double,
    ) = saveCoinPriseConversionUseCase(coinInfo, amount)
}