package com.example.cryptoapp.presentation.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptoapp.domain.entity.news.NewsInfo

class NewsDiffCallBack : DiffUtil.ItemCallback<NewsInfo>() {

    override fun areItemsTheSame(oldItem: NewsInfo, newItem: NewsInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NewsInfo, newItem: NewsInfo): Boolean {
        return oldItem == newItem
    }
}