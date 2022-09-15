package com.example.cryptoconverter.kaspresso.screen

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.example.cryptoconverter.R

object CoinConvertScreen : Screen<CoinConvertScreen>() {

    val amountCurrencyToConvert = KEditText { withId(R.id.edt_amount_currency_to_convert) }
    val currencyConvertEdt = KTextView { withId(R.id.tv_currency_convert_edt) }
    val convertResult = KTextView { withId(R.id.tv_convert_result) }
    val currencyConvertResult = KTextView { withId(R.id.tv_convert_result_currency) }
}