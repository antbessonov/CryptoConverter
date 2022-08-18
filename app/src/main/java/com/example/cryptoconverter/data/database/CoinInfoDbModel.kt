package com.example.cryptoconverter.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_coins")
data class CoinInfoDbModel(
    @PrimaryKey
    val id: Int?,
    val name: String?,
    val symbol: String?,
    val cmcRank: Int?,
    val tags: String?,
    val lastUpdated: String?,
    val price: Double?,
)