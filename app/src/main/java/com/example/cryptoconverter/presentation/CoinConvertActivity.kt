package com.example.cryptoconverter.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
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
        viewModel.getCoinInfo(idCoin).observe(this) {
            with(binding) {
                Picasso.get().load(it.url).into(ivLogoCoin)
                tvSymbolCoin.text = it.symbol
                tvNameCoin.text = it.name
                tvPriceCoin.text = it.price
            }
        }
    }
}