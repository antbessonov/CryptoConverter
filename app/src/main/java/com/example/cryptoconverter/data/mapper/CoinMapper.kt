package com.example.cryptoconverter.data.mapper

import com.example.cryptoconverter.data.database.CoinInfoDbModel
import com.example.cryptoconverter.data.network.model.CoinInfoDto
import com.example.cryptoconverter.data.network.model.CoinPriceConversionDto
import com.example.cryptoconverter.domain.CoinInfo
import com.example.cryptoconverter.domain.CoinPriceConversion
import java.text.SimpleDateFormat
import java.util.*

class CoinMapper {

    companion object {

        private const val BASE_ICON_URL = "https://s2.coinmarketcap.com/static/img/coins/64x64/"
        private const val ICON_FORMAT = ".png"
        private const val EMPTY_DATE = ""
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
        id = dbModel.id,
        name = dbModel.name,
        symbol = dbModel.symbol,
        lastUpdated = convertTime(dbModel.lastUpdated),
        price = String.format("%,.7f", dbModel.price),
        url = BASE_ICON_URL + dbModel.id + ICON_FORMAT
    )

    fun mapDtoTOEntity(dto: CoinPriceConversionDto?) = CoinPriceConversion(
        id = dto?.id,
        symbol = dto?.symbol,
        name = dto?.name,
        amount = dto?.amount,
        lastUpdated = convertTime(dto?.lastUpdated),
        price = String.format("%,.7f", dto?.quote?.values?.first()?.price),
        convertId = dto?.quote?.keys?.first()?.toInt()
    )

    private fun convertTime(dateString: String?): String {
        if (dateString == null) return EMPTY_DATE
        val patternInput = "yyyy-MM-dd'T'HH:mm:ss.SSSX"
        val sdfInput = SimpleDateFormat(patternInput, Locale.getDefault())
        val date = sdfInput.parse(dateString) ?: return EMPTY_DATE
        val patternOutput = "HH:mm"
        val sdfOutput = SimpleDateFormat(patternOutput, Locale.getDefault())
        sdfOutput.timeZone = TimeZone.getDefault()
        return sdfOutput.format(date)
    }
}