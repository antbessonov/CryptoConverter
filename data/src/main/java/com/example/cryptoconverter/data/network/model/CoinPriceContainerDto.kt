package com.example.cryptoconverter.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinPriceContainerDto(
    @SerializedName("USD")
    @Expose
    val usd: CoinPriceUsdDto?,
)