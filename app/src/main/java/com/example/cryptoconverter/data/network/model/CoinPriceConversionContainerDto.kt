package com.example.cryptoconverter.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinPriceConversionContainerDto (
    @SerializedName("data")
    @Expose
    val data: CoinPriceConversionDto?
)