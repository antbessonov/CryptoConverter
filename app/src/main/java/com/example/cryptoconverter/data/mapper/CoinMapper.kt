package com.example.cryptoconverter.data.mapper

import com.example.cryptoconverter.data.database.CoinInfoDbModel
import com.example.cryptoconverter.data.network.model.CoinInfoDto
import com.example.cryptoconverter.domain.CoinInfo

class CoinMapper {

    fun mapDtoToDbModel(dto: CoinInfoDto) = CoinInfoDbModel(
        id = dto.id,
        name = dto.name,
        symbol = dto.symbol,
        tags = dto.tags.toString(),
        lastUpdated = dto.lastUpdated,
        price = dto.coinPriceContainer?.usd?.price
    )

    fun mapDbModelToEntity(dbModel: CoinInfoDbModel) = CoinInfo(
        id = dbModel.id.toString(),
        name = dbModel.name,
        symbol = dbModel.symbol,
        tags = dbModel.tags,
        lastUpdated = dbModel.lastUpdated,
        price = dbModel.price.toString()
    )
}