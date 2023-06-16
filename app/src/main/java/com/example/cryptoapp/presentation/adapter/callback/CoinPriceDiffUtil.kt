package com.example.cryptoapp.presentation.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptoapp.data.network.model.coininfo.CoinInfoDto

class CoinPriceDiffUtil : DiffUtil.ItemCallback<CoinInfoDto>(){

    override fun areItemsTheSame(oldItem: CoinInfoDto, newItem: CoinInfoDto): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfoDto, newItem: CoinInfoDto): Boolean {
        return oldItem == newItem
    }
}