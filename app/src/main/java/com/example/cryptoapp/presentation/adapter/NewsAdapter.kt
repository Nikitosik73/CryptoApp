package com.example.cryptoapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cryptoapp.R
import com.example.cryptoapp.presentation.adapter.callback.NewsDiffCallBack
import com.example.cryptoapp.presentation.adapter.holder.NewsViewHolder
import com.example.cryptoapp.data.network.model.news.Data
import com.example.cryptoapp.databinding.ItemNewsBinding
import com.squareup.picasso.Picasso

class NewsAdapter(val context: Context) : ListAdapter<Data, NewsViewHolder>(
    NewsDiffCallBack()
) {
    var onCLickNewsListener: ((Data) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).apply { return NewsViewHolder(this) }
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = getItem(position)
        val binding = holder.binding

        with(binding) {
            with(newsItem) {
                val templateTitleNews = context.resources.getString(R.string.title_news)
                nameNews.text = sourceInfo?.name
                titleNews.text = String.format(templateTitleNews, title)
                Picasso.get().load(imageUrl).into(imageNews)
                root.setOnClickListener {
                    onCLickNewsListener?.invoke(this)
                }
            }
        }
    }
}