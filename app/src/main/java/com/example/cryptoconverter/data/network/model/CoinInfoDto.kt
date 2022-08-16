package com.example.cryptoconverter.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfoDto(
    @SerializedName("id")
    @Expose
    val id: Int?,

    @SerializedName("name")
    @Expose
    val name: String?,

    @SerializedName("symbol")
    @Expose
    val symbol: String?,

    @SerializedName("tags")
    @Expose
    val tags: List<String>?,

    @SerializedName("last_updated")
    @Expose
    val lastUpdated: String?,

    @SerializedName("quote")
    @Expose
    val coinPriceContainer: CoinPriceContainerDto?,
)