package com.example.cryptoapp.data.network.model.news

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TitleNewsDto(
    @SerializedName("name")
    val name: String
): Serializable
