package com.example.cryptoconverter.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIFactory {

    private const val BASE_URL = "https://pro-api.coinmarketcap.com/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val apiService: APIService = retrofit.create(APIService::class.java)
}