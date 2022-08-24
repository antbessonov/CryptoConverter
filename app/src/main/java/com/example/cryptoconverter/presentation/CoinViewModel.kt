package com.example.cryptoconverter.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoconverter.data.repository.CoinRepositoryImpl
import com.example.cryptoconverter.domain.GetCoinInfoListUseCase
import com.example.cryptoconverter.domain.GetCoinInfoUseCase
import com.example.cryptoconverter.domain.GetCoinPriceConversionUseCase
import com.example.cryptoconverter.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)
    private val getCoinPriceConversionUseCase = GetCoinPriceConversionUseCase(repository)

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }

    val coinInfoList = getCoinInfoListUseCase()

    fun getCoinInfo(id: Int) = getCoinInfoUseCase(id)

    suspend fun getCoinConversion(
        amount: Int,
        id: Int,
        currencyConvertID: Int,
    ) = getCoinPriceConversionUseCase(amount, id, currencyConvertID)
}