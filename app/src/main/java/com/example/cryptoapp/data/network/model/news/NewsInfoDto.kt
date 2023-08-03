package com.example.cryptoapp.data.network.model.news

import com.google.gson.annotations.SerializedName

data class NewsInfoDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("guid")
    val guid: String,
    @SerializedName("imageurl")
    val imageUrl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("source_info")
    val titleNews: TitleNewsDto
)
