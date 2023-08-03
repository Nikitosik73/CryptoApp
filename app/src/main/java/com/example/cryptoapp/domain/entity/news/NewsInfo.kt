package com.example.cryptoapp.domain.entity.news

import java.io.Serializable

data class NewsInfo(
    val id: Int,
    val guid: String,
    val imageUrl: String,
    val title: String,
    val body: String,
    val titleNews: String
): Serializable