package com.example.cryptoconverter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoconverter.domain.model.CoinInfo
import com.example.cryptoconverter.domain.usecase.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase,
    private val saveCoinPriseConversionUseCase: SaveCoinPriseConversionUseCase,
    private val getCoinPriseConversionUseCase: GetCoinPriseConversionUseCase,
) : ViewModel() {

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