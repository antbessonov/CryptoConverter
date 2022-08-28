package com.example.cryptoconverter.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoconverter.R
import com.example.cryptoconverter.databinding.ActivityCoinConvertBinding
import com.squareup.picasso.Picasso

class CoinConvertActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_ID_COIN = "id_coin"
        private const val DEFAULT_ID_COIN = 0

        fun newIntent(context: Context, idCoin: Int): Intent {
            val intent = Intent(context, CoinConvertActivity::class.java)
            intent.putExtra(EXTRA_ID_COIN, idCoin)
            return intent
        }
    }

    private val binding by lazy {
        ActivityCoinConvertBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[CoinViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (!intent.hasExtra(EXTRA_ID_COIN)) {
            finish()
            return
        }

        val idCoin = intent.getIntExtra(EXTRA_ID_COIN, DEFAULT_ID_COIN)
        val amountDefault = binding.edtAmountCurrencyToConvert.hint.toString().toDouble()

        viewModel.getCoinInfo(idCoin).observe(this) {
            with(binding) {
                Picasso.get().load(it.url).into(ivLogoCoin)
                tvSymbolCoin.text = it.symbol
                tvNameCoin.text = it.name
                tvPriceCoin.text = String.format("%,.7f", it.price)
                tvLastUpdated.text = String.format(
                    getString(R.string.last_update_template),
                    it.lastUpdated
                )
                if (edtAmountCurrencyToConvert.text.isNullOrEmpty()) {
                    viewModel.saveCoinPriseConversion(it, amountDefault)
                }
                edtAmountCurrencyToConvert.setOnKeyListener { view, i, keyEvent ->
                    if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                        val amount = binding.edtAmountCurrencyToConvert.text
                            .toString()
                            .toDouble()
                        viewModel.saveCoinPriseConversion(it, amount)
                        binding.edtAmountCurrencyToConvert.clearFocus()
                        val imm = view.context.getSystemService(
                            Context.INPUT_METHOD_SERVICE
                        ) as InputMethodManager
                        imm.hideSoftInputFromWindow(view.windowToken, 0)
                        return@setOnKeyListener true
                    }
                    return@setOnKeyListener false
                }
                tvConvertResultCurrency.text = it.symbol
            }
        }

        viewModel.coinPriseConversion.observe(this) {
            binding.tvConvertResult.text = String.format("%,.7f", it.priceConversion)
        }
    }
}