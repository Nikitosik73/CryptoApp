package com.example.cryptoapp.pojo.news

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("Data")
    val data: List<Data>?
)
