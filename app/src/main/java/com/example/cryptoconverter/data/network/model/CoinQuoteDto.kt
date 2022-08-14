package com.example.cryptoconverter.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinQuoteDto (
    @SerializedName("USD")
    @Expose
    private val usd: CoinPriceUsdDto? = null
)