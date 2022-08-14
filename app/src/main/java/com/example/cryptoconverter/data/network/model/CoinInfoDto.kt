package com.example.cryptoconverter.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfoDto (
    @SerializedName("id")
    @Expose
    private val id: Int? = null,

    @SerializedName("name")
    @Expose
    private val name: String? = null,

    @SerializedName("symbol")
    @Expose
    private val symbol: String? = null,

    @SerializedName("tags")
    @Expose
    private val tags: List<String>? = null,

    @SerializedName("last_updated")
    @Expose
    private val lastUpdated: String? = null,

    @SerializedName("quote")
    @Expose
    private val quote: CoinQuoteDto? = null,
)