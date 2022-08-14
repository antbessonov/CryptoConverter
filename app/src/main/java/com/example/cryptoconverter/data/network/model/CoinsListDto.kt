package com.example.cryptoconverter.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinsListDto (
    @SerializedName("data")
    @Expose
    private val data: List<CoinInfoDto>? = null
)