package com.example.cryptoapp.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptoapp.pojo.priceinfo.CoinPriceInfo

class CoinPriceDiffUtil : DiffUtil.ItemCallback<CoinPriceInfo>(){

    override fun areItemsTheSame(oldItem: CoinPriceInfo, newItem: CoinPriceInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinPriceInfo, newItem: CoinPriceInfo): Boolean {
        return oldItem == newItem
    }
}