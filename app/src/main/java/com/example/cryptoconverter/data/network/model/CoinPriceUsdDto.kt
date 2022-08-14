package com.example.cryptoconverter.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinPriceUsdDto(
    @SerializedName("price")
    @Expose
    private val price: Double? = null,
)