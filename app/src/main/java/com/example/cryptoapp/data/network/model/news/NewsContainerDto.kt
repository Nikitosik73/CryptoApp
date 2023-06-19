package com.example.cryptoapp.data.network.model.news

import com.google.gson.annotations.SerializedName

data class NewsContainerDto(
    @SerializedName("Data")
    val data: List<NewsInfoDto>?
)
