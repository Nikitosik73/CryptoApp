package com.example.cryptoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cryptoapp.R
import com.example.cryptoapp.pojo.priceinfo.CoinPriceInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(val context: Context) : Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var coinInfoList: List<CoinPriceInfo> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinCLickListener: OnCoinCLickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_coin_info,
            parent,
            false
        )
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        with(holder) {
            with(coin) {
                val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
                textViewCoinName.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
                textViewPriceInfo.text = price
                textViewLastUpdate.text = String.format(lastUpdateTemplate, getFormattedTime())
                Picasso.get().load(getFullImageURL()).into(imageViewLogoCoin)
                itemView.setOnClickListener {
                    onCoinCLickListener?.onCoinClick(this)
                }
            }
        }

    }

    override fun getItemCount() = coinInfoList.size

    interface OnCoinCLickListener {
        fun onCoinClick(coinPriceInfo: CoinPriceInfo)
    }

    inner class CoinInfoViewHolder(itemView: View) : ViewHolder(itemView) {
        val imageViewLogoCoin = itemView.findViewById<ImageView>(R.id.imageViewLogoCoin)
        val textViewCoinName = itemView.findViewById<TextView>(R.id.textViewCoinName)
        val textViewPriceInfo = itemView.findViewById<TextView>(R.id.textViewPriceInfo)
        val textViewLastUpdate = itemView.findViewById<TextView>(R.id.textViewLastUpdate)
    }
}