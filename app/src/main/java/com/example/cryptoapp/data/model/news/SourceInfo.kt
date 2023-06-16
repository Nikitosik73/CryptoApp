package com.example.cryptoapp.data.model.news

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SourceInfo(
    @SerializedName("name")
    val name: String?
) : Serializable
