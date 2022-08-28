package com.example.cryptoconverter.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptoconverter.domain.model.CoinInfo

object CoinInfoDiffCallback : DiffUtil.ItemCallback<CoinInfo>() {

    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem == newItem
    }
}