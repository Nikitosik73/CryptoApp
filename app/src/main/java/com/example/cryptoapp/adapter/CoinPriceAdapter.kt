package com.example.cryptoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cryptoapp.R
import com.example.cryptoapp.adapter.callback.CoinPriceDiffUtil
import com.example.cryptoapp.adapter.holder.CoinInfoViewHolder
import com.example.cryptoapp.databinding.ItemCoinInfoBinding
import com.example.cryptoapp.pojo.priceinfo.CoinPriceInfo
import com.squareup.picasso.Picasso

class CoinPriceAdapter(
    val context: Context
) : ListAdapter<CoinPriceInfo, CoinInfoViewHolder>(
    CoinPriceDiffUtil()
) {

    var onClickCoinPriceInfo: ((CoinPriceInfo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).apply { return CoinInfoViewHolder(this) }
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val itemCoin = getItem(position)
        val binding = holder.binding

        with(binding) {
            with(itemCoin) {
                val templateCoinName = context.resources.getString(R.string.symbols_template)
                val templateTime = context.resources.getString(R.string.last_update_template)
                textViewCoinName.text = String.format(templateCoinName, fromSymbol, toSymbol)
                textViewPriceInfo.text = price
                textViewLastUpdate.text = String.format(templateTime, getFormattedTime())
                Picasso.get().load(getFullImageURL()).into(imageViewLogoCoin)
                binding.root.setOnClickListener {
                    onClickCoinPriceInfo?.invoke(this)
                }
            }
        }
    }
}