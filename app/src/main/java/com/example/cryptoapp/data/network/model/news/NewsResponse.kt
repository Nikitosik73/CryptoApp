package com.example.cryptoapp.data.network.model.news

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("Data")
    val data: List<Data>?
)
