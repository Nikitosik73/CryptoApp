package com.example.cryptoapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.cryptoapp.domain.entity.news.NewsInfo

interface NewsRepository {

    fun getNews(): LiveData<List<NewsInfo>>

    suspend fun loadNews()
}