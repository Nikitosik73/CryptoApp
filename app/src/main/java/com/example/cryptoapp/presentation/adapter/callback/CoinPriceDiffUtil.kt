package com.example.cryptoapp.presentation.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptoapp.domain.entity.coin.CoinInfo

class CoinPriceDiffUtil : DiffUtil.ItemCallback<CoinInfo>(){

    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem == newItem
    }
}