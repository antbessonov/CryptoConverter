package com.example.cryptoconverter.domain

import androidx.lifecycle.LiveData

interface CoinRepository {

    fun getCoinInfoList(): LiveData<List<CoinInfo>>

    fun getCoinInfo(id: Int): LiveData<CoinInfo>
}