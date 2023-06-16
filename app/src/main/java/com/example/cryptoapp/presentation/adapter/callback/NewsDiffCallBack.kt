package com.example.cryptoapp.presentation.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptoapp.data.network.model.news.Data

class NewsDiffCallBack : DiffUtil.ItemCallback<Data>() {

    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }
}