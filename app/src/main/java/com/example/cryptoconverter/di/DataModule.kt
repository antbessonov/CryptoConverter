package com.example.cryptoconverter.di

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.cryptoconverter.data.database.AppDatabase
import com.example.cryptoconverter.data.database.CoinInfoDao
import com.example.cryptoconverter.data.network.APIFactory
import com.example.cryptoconverter.data.network.APIService
import com.example.cryptoconverter.data.repository.CoinRepositoryImpl
import com.example.cryptoconverter.data.sharedprefs.CoinPriseConversionStorage
import com.example.cryptoconverter.data.sharedprefs.SharedPrefsCoinPriseConversionStorage
import com.example.cryptoconverter.domain.model.CoinPriceConversion
import com.example.cryptoconverter.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    @Binds
    @ApplicationScope
    fun bindCoinPriseConversionStorage(
        impl: SharedPrefsCoinPriseConversionStorage,
    ): CoinPriseConversionStorage

    companion object {

        @Provides
        @ApplicationScope
        fun provideCoinInfoDao(
            application: Application,
        ): CoinInfoDao {
            return AppDatabase.getInstance(application).coinsInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideAPIService(): APIService {
            return APIFactory.apiService
        }

        @Provides
        @ApplicationScope
        fun provideCoinPriceConversionLive(): MutableLiveData<CoinPriceConversion> {
            return MutableLiveData<CoinPriceConversion>()
        }
    }
}