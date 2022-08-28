package com.example.cryptoconverter.domain.repository

import androidx.lifecycle.LiveData
import com.example.cryptoconverter.domain.model.CoinInfo
import com.example.cryptoconverter.domain.model.CoinPriceConversion

interface CoinRepository {

    fun getCoinInfoList(): LiveData<List<CoinInfo>>

    fun getCoinInfo(id: Int): LiveData<CoinInfo>

    suspend fun loadData()

    fun saveCoinPriseConversion(coinInfo: CoinInfo, amount: Double)

    fun getCoinPriseConversion(): LiveData<CoinPriceConversion>
}