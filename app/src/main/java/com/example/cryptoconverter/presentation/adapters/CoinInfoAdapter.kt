package com.example.cryptoconverter.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cryptoconverter.R
import com.example.cryptoconverter.databinding.ItemCoinInfoBinding
import com.example.cryptoconverter.domain.CoinInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(
    private val context: Context,
) : ListAdapter<CoinInfo, CoinInfoViewHolder>(CoinInfoDiffCallback) {

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = getItem(position)
        val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
        Picasso.get().load(coin.url).into(holder.binding.ivLogoCoin)
        holder.binding.tvSymbolCoin.text = coin.symbol
        holder.binding.tvNameCoin.text = coin.name
        holder.binding.tvPriceCoin.text = coin.price
        holder.binding.tvLastUpdated.text = String.format(lastUpdateTemplate, coin.lastUpdated)
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinInfo: CoinInfo)
    }
}