package com.example.cryptoconverter.kaspresso

import android.view.KeyEvent
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cryptoconverter.kaspresso.screen.CoinConvertScreen
import com.example.cryptoconverter.kaspresso.screen.CoinPriceListScreen
import com.example.cryptoconverter.presentation.CoinPriceListActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CoinPriceListTest : TestCase() {

    @get:Rule
    val activityRule = ActivityScenarioRule(
        CoinPriceListActivity::class.java
    )

    @Test
    fun checkCoinInfoDisplayed() {
        run {
            step("Check coins content") {
                checkCoins(
                    CoinInfo(
                        symbol = "BTC",
                        name = "Bitcoin",
                        currencyConvert = "USD",
                    ),
                    CoinInfo(
                        symbol = "ETH",
                        name = "Ethereum",
                        currencyConvert = "USD",
                    ),
                    CoinInfo(
                        symbol = "USDT",
                        name = "Tether",
                        currencyConvert = "USD",
                    ),
                    CoinInfo(
                        symbol = "USDC",
                        name = "USD Coin",
                        currencyConvert = "USD",
                    ),
                    CoinInfo(
                        symbol = "BNB",
                        name = "BNB",
                        currencyConvert = "USD",
                    ),
                )
            }
        }
    }

    @Test
    fun checkCoinPriceDisplayed() {
        run {
            step("Check price coins") {
                CoinPriceListScreen {
                    coinPriceList {
                        (0 until getSize()).forEach { i ->
                            scrollTo(i)
                            childAt<CoinPriceListScreen.CoinInfoItem>(i) {
                                price {
                                    hasAnyText()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    fun checkSizeCoinInfoList() {
        run {
            step("Check size recyclerview") {
                CoinPriceListScreen {
                    coinPriceList {
                        hasSize(size = LIMIT_CURRENCY + 1)
                    }
                }
            }
        }
    }

    @Test
    fun checkCoinConversion() {
        run {
            step("") {
                CoinPriceListScreen {
                    coinPriceList {
                        firstChild<CoinPriceListScreen.CoinInfoItem> {
                            click()
                        }
                    }
                }
            }
            step("") {
                CoinConvertScreen {
                    amountCurrencyToConvert {
                        hasEmptyText()
                        hasHint(DEFAULT_AMOUNT_CURRENCY_TO_CONVERT)
                        typeText(AMOUNT_CURRENCY_TO_CONVERT)
                        KeyEvent.KEYCODE_ENTER
                    }
                    convertResult {
                        isDisplayed()
                        hasAnyText()
                    }
                }
            }
        }
    }

    class CoinInfo(
        val symbol: String,
        val name: String,
        val currencyConvert: String,
    )

    private fun checkCoins(vararg coins: CoinInfo) {
        coins.forEachIndexed { index, coinInfo ->
            CoinPriceListScreen {
                coinPriceList {
                    childAt<CoinPriceListScreen.CoinInfoItem>(index) {
                        logo {
                            isDisplayed()
                        }

                        symbol {
                            isDisplayed()
                            hasText(coinInfo.symbol)
                        }

                        name {
                            isDisplayed()
                            hasText(coinInfo.name)
                        }

                        price {
                            isDisplayed()
                            hasAnyText()
                        }

                        currencyConvert {
                            isDisplayed()
                            hasText(coinInfo.currencyConvert)
                        }

                        lastUpdated {
                            isDisplayed()
                            hasAnyText()
                        }
                    }
                }
            }
        }
    }

    companion object {

        private const val LIMIT_CURRENCY = 15
        private const val DEFAULT_AMOUNT_CURRENCY_TO_CONVERT = "100"
        private const val AMOUNT_CURRENCY_TO_CONVERT = "1000"
    }
}