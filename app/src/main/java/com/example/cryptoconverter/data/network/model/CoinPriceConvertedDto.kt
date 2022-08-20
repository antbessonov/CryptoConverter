package com.example.cryptoconverter.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinPriceConvertedDto(
    @SerializedName("price")
    @Expose
    val price: Double?,

    @SerializedName("last_updated")
    @Expose
    val lastUpdated: String?,
)