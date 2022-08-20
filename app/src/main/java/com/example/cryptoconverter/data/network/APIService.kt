package com.example.cryptoconverter.data.network

import com.example.cryptoconverter.data.network.model.CoinInfoListContainerDto
import com.example.cryptoconverter.data.network.model.CoinPriceConversionContainerDto
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    companion object {

        private const val QUERY_PARAM_API_KEY = "CMC_PRO_API_KEY"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_CURRENCY_CONVERT = "convert"

        private const val QUERY_PARAM_AMOUNT_CURRENCY_TO_CONVERT = "amount"
        private const val QUERY_PARAM_CURRENCY_ID = "id"
        private const val QUERY_PARAM_CURRENCY_CONVERT_ID = "convert"

        private const val API_KEY = "23571264-9dc3-417b-ab36-3b7dccfdd97f"
        private const val LIMIT = 15
        private const val CURRENCY_CONVERT = "USD"

        private const val CURRENCY_ID = 2781
        private const val CURRENCY_CONVERT_ID = 1
    }

    @GET("v1/cryptocurrency/listings/latest")
    suspend fun getCoinInfoList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_LIMIT) limit: Int = LIMIT,
        @Query(QUERY_PARAM_CURRENCY_CONVERT) currencyConvert: String = CURRENCY_CONVERT,
    ): CoinInfoListContainerDto

    @GET("/v2/tools/price-conversion")
    suspend fun getCoinPriceConversion(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_AMOUNT_CURRENCY_TO_CONVERT) amount: Int,
        @Query(QUERY_PARAM_CURRENCY_ID) id: Int = CURRENCY_ID,
        @Query(QUERY_PARAM_CURRENCY_CONVERT_ID) currencyConvertID: Int = CURRENCY_CONVERT_ID,
    ): CoinPriceConversionContainerDto
}