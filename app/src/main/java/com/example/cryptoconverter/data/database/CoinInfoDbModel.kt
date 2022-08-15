package com.example.cryptoconverter.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_coins")
data class CoinInfoDbModel(
    @PrimaryKey
    val id: Int? = null,
    val name: String? = null,
    val symbol: String? = null,
    val tags: List<String>? = null,
    val lastUpdated: String? = null,
    val price: Double? = null,
)