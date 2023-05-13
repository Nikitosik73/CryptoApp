package com.example.cryptoapp.adapter.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cryptoapp.R

class NewsViewHolder(itemView: View) : ViewHolder(itemView) {
    val imageNews = itemView.findViewById<ImageView>(R.id.imageNews)
    val nameNews = itemView.findViewById<TextView>(R.id.nameNews)
    val titleNews = itemView.findViewById<TextView>(R.id.titleNews)
}