package com.example.cryptoconverter.data.network

import com.example.cryptoconverter.data.network.model.CoinInfoListContainerDto
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    companion object {

        private const val QUERY_PARAM_API_KEY = "CMC_PRO_API_KEY"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_CURRENCY_CONVERT = "convert"

        private const val API_KEY = "23571264-9dc3-417b-ab36-3b7dccfdd97f"
        private const val LIMIT = 15
        private const val CURRENCY_CONVERT = "USD"
    }

    @GET("v1/cryptocurrency/listings/latest")
    suspend fun getCoinInfoList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_LIMIT) limit: Int = LIMIT,
        @Query(QUERY_PARAM_CURRENCY_CONVERT) currencyConvert: String = CURRENCY_CONVERT,
    ): CoinInfoListContainerDto
}