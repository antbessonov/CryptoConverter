package com.example.cryptoconverter.data.mapper

import com.example.cryptoconverter.data.database.CoinInfoDbModel
import com.example.cryptoconverter.data.network.model.CoinInfoDto
import com.example.cryptoconverter.domain.CoinInfo

class CoinMapper {

    companion object {

        const val BASE_ICON_URL = "https://s2.coinmarketcap.com/static/img/coins/64x64/"
        const val ICON_FORMAT = ".png"
    }

    fun mapDtoToDbModel(dto: CoinInfoDto) = CoinInfoDbModel(
        id = dto.id,
        name = dto.name,
        symbol = dto.symbol,
        cmcRank = dto.cmcRank,
        tags = dto.tags.toString(),
        lastUpdated = dto.lastUpdated,
        price = dto.coinPriceContainer?.usd?.price
    )

    fun mapDbModelToEntity(dbModel: CoinInfoDbModel) = CoinInfo(
        id = dbModel.id.toString(),
        name = dbModel.name,
        symbol = dbModel.symbol,
        lastUpdated = dbModel.lastUpdated,
        price = String.format("%,.8f", dbModel.price),
        url = BASE_ICON_URL + dbModel.id + ICON_FORMAT
    )
}