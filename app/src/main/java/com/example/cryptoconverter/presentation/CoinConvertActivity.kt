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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

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

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (!intent.hasExtra(EXTRA_ID_COIN)) {
            finish()
            return
        }
        val idCoin = intent.getIntExtra(EXTRA_ID_COIN, DEFAULT_ID_COIN)
        val lastUpdateTemplate = getString(R.string.last_update_template)
        viewModel.getCoinInfo(idCoin).observe(this) {
            with(binding) {
                Picasso.get().load(it.url).into(ivLogoCoin)
                tvSymbolCoin.text = it.symbol
                tvNameCoin.text = it.name
                tvPriceCoin.text = it.price
                tvLastUpdated.text = String.format(lastUpdateTemplate, it.lastUpdated)
                tvConvertResultCurrency.text = it.symbol
            }
        }
        binding.edtAmountCurrencyToConvert.setOnKeyListener { p0, p1, p2 ->
            if (p2.action == KeyEvent.ACTION_DOWN && p1 == KeyEvent.KEYCODE_ENTER) {

                val amount = binding.edtAmountCurrencyToConvert.text.toString().toInt()

                coroutineScope.launch {
                    viewModel.getCoinConversion(
                        amount,
                        2781,
                        idCoin
                    ).observe(this@CoinConvertActivity) {
                        binding.tvConvertResult.text = it.price
                        binding.tvLastUpdatedConvertResult.text = String.format(
                            lastUpdateTemplate,
                            it.lastUpdated
                        )
                    }
                    viewModel.getCoinConversion(
                        1,
                        idCoin,
                        2781
                    ).observe(this@CoinConvertActivity) {
                        binding.tvPriceCoin.text = it.price
                        binding.tvLastUpdated.text = String.format(
                            lastUpdateTemplate,
                            it.lastUpdated
                        )
                    }
                }

                binding.edtAmountCurrencyToConvert.clearFocus()
                val imm =
                    p0.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(p0.windowToken, 0)

                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}