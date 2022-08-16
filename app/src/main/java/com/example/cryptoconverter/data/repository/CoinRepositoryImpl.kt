package com.example.cryptoconverter.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.cryptoconverter.data.database.AppDatabase
import com.example.cryptoconverter.data.mapper.CoinMapper
import com.example.cryptoconverter.data.network.APIFactory
import com.example.cryptoconverter.domain.CoinInfo
import com.example.cryptoconverter.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(
    application: Application,
) : CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinsInfoDao()
    private val apiService = APIFactory.apiService

    private val mapper = CoinMapper()

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return Transformations.map(coinInfoDao.getCoinInfoList()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getCoinInfo(id: Int): LiveData<CoinInfo> {
        return Transformations.map(coinInfoDao.getCoinInfo(id)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override suspend fun loadData() {
        while (true) {
            try {
                val coinInfoListDto = apiService.getCoinInfoList().data
                coinInfoListDto?.let {
                    coinInfoDao.insertCoinInfoList(
                        it.map {
                            mapper.mapDtoToDbModel(it)
                        }
                    )
                }
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }
}