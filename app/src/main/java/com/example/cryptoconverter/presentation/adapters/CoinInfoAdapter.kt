package com.example.cryptoconverter.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cryptoconverter.databinding.ItemCoinInfoBinding
import com.example.cryptoconverter.domain.CoinInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter : ListAdapter<CoinInfo, CoinInfoViewHolder>(CoinInfoDiffCallback) {

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
        holder.binding.tvSymbolCoin.text = coin.symbol
        holder.binding.tvNameCoin.text = coin.name
        holder.binding.tvPriceCoin.text = coin.price
        Picasso.get().load(coin.url).into(holder.binding.ivLogoCoin)
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinInfo: CoinInfo)
    }
}