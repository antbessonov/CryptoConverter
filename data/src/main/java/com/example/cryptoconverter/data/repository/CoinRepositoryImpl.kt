package com.example.cryptoconverter.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.cryptoconverter.data.database.AppDatabase
import com.example.cryptoconverter.data.mapper.CoinInfoMapper
import com.example.cryptoconverter.data.mapper.CoinPriseConversionMapper
import com.example.cryptoconverter.data.network.APIFactory
import com.example.cryptoconverter.data.sharedprefs.SharedPrefsCoinPriseConversionStorage
import com.example.cryptoconverter.domain.model.CoinInfo
import com.example.cryptoconverter.domain.model.CoinPriceConversion
import com.example.cryptoconverter.domain.repository.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(
    application: Application,
) : CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinsInfoDao()
    private val apiService = APIFactory.apiService

    private val sharedPreferences = SharedPrefsCoinPriseConversionStorage(application)

    private val coinInfoMapper = CoinInfoMapper()
    private val coinPriseConversionMapper = CoinPriseConversionMapper()

    private val coinPriceConversionLive = MutableLiveData<CoinPriceConversion>()

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return Transformations.map(coinInfoDao.getCoinInfoList()) {
            it.map {
                coinInfoMapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getCoinInfo(id: Int): LiveData<CoinInfo> {
        return Transformations.map(coinInfoDao.getCoinInfo(id)) {
            coinInfoMapper.mapDbModelToEntity(it)
        }
    }

    override suspend fun loadData() {
        while (true) {
            try {
                val coinInfoListDto = apiService.getCoinInfoList().data
                coinInfoListDto?.let {
                    coinInfoDao.insertCoinInfoList(
                        it.map {
                            coinInfoMapper.mapDtoToDbModel(it)
                        }
                    )
                }
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }

    override fun saveCoinPriseConversion(coinInfo: CoinInfo, amount: Double) {
        val priseConversion = coinPriseConversionMapper.priceConversion(coinInfo, amount)
        val coinPriceConversion = CoinPriceConversion(priceConversion = priseConversion)
        sharedPreferences.save(
            coinPriseConversionMapper.mapEntityToSharedPrefs(coinPriceConversion)
        )
        coinPriceConversionLive.value = coinPriceConversion
    }

    override fun getCoinPriseConversion(): LiveData<CoinPriceConversion> {
        val coinPriceConversion = coinPriseConversionMapper
            .mapSharedPrefsToEntity(sharedPreferences.get())
        coinPriceConversionLive.value = coinPriceConversion
        return coinPriceConversionLive
    }
}