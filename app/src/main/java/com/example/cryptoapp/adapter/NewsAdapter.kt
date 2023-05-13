package com.example.cryptoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cryptoapp.R
import com.example.cryptoapp.adapter.callback.NewsDiffCallBack
import com.example.cryptoapp.adapter.holder.NewsViewHolder
import com.example.cryptoapp.pojo.news.Data
import com.squareup.picasso.Picasso

class NewsAdapter(val context: Context) : ListAdapter<Data, NewsViewHolder>(
    NewsDiffCallBack()
) {
    var onCLickNewsListener: ((Data) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_news, parent, false
        )
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = getItem(position)
        with(holder) {
            with(newsItem) {
                val templateTitleNews = context.resources.getString(R.string.title_news)
                nameNews.text = sourceInfo?.name
                titleNews.text = String.format(templateTitleNews, title)
                Picasso.get().load(imageUrl).into(imageNews)
                itemView.setOnClickListener {
                    onCLickNewsListener?.invoke(this)
                }
            }
        }
    }
}