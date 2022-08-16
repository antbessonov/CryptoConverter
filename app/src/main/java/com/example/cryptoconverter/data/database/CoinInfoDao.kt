package com.example.cryptoconverter.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoinInfoDao {

    @Query("SELECT * FROM crypto_coins")
    fun getCoinInfoList(): LiveData<List<CoinInfoDbModel>>

    @Query("SELECT * FROM crypto_coins WHERE id == :id LIMIT 1")
    fun getCoinInfo(id: Int): LiveData<CoinInfoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoinInfoList(coinInfoList: List<CoinInfoDbModel>)
}