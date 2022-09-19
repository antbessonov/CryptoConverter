package com.example.cryptoconverter.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoconverter.app.CoinApp
import com.example.cryptoconverter.databinding.ActivityCoinPriceListBinding
import com.example.cryptoconverter.domain.model.CoinInfo
import com.example.cryptoconverter.presentation.adapters.CoinInfoAdapter
import javax.inject.Inject

class CoinPriceListActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
    }

    private val component by lazy {
        (application as CoinApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = CoinInfoAdapter(this)
        binding.rvCoinPriceList.adapter = adapter
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinInfo: CoinInfo) {
                val intent = coinInfo.id?.let {
                    CoinConvertActivity.newIntent(
                        this@CoinPriceListActivity,
                        it
                    )
                }
                startActivity(intent)
            }
        }

        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }
    }
}