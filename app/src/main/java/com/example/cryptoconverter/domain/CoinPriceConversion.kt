package com.example.cryptoconverter.domain

data class CoinPriceConversion(
    val id: Int?,
    val symbol: String?,
    val name: String?,
    val amount: Int?,
    val lastUpdated: String?,
    val price: String,
    val convertId: Int?
)