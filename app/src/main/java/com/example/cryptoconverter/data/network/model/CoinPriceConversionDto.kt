package com.example.cryptoconverter.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinPriceConversionDto(
    @SerializedName("id")
    @Expose
    val id: Int?,

    @SerializedName("symbol")
    @Expose
    val symbol: String?,

    @SerializedName("name")
    @Expose
    val name: String?,

    @SerializedName("amount")
    @Expose
    val amount: Int?,

    @SerializedName("last_updated")
    @Expose
    val lastUpdated: String?,

    @SerializedName("quote")
    @Expose
    val quote: Map<String, CoinPriceConvertedDto>?,
)