package com.example.cryptoconverter.data.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cryptoconverter.data.tools.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var db: AppDatabase
    private lateinit var dao: CoinInfoDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.coinsInfoDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndReadCoinInfoListDbModel() = runBlocking {
        val coinInfoListDbModel = listOf(CoinInfoDbModel(
            id = 1,
            name = "Bitcoin",
            symbol = "BTC",
            cmcRank = 1,
            tags = "coin",
            lastUpdated = "2022-09-02T10:10:00.000Z",
            price = 19523.1234
        ))
        dao.insertCoinInfoList(coinInfoListDbModel)
        val coinInfoListDbModelInDb = dao.getCoinInfoList().getOrAwaitValue()
        assertThat(coinInfoListDbModelInDb.containsAll(coinInfoListDbModel)).isTrue()
    }

}