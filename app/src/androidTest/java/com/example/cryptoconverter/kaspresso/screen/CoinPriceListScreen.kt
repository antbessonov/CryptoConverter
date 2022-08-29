package com.example.cryptoconverter.kaspresso.screen

import android.view.View
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.example.cryptoconverter.R
import org.hamcrest.Matcher

object CoinPriceListScreen : Screen<CoinPriceListScreen>() {

    val coinPriceList = KRecyclerView(
        builder = { withId(R.id.rv_coin_price_list) },
        itemTypeBuilder = { itemType(::CoinInfoItem) }
    )

    class CoinInfoItem(parent: Matcher<View>) : KRecyclerItem<CoinInfoItem>(parent) {

        val logo = KImageView(parent) { withId(R.id.iv_logo_coin) }
        val symbol = KTextView(parent) { withId(R.id.tv_symbol_coin) }
        val name = KTextView(parent) { withId(R.id.tv_name_coin) }
        val price = KTextView(parent) { withId(R.id.tv_price_coin) }
        val currencyConvert = KTextView(parent) { withId(R.id.tv_currency_convert) }
        val lastUpdated = KTextView(parent) { withId(R.id.tv_last_updated) }
    }
}