package com.example.cryptoapp.data.model.news

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Data(
    @SerializedName("id")
    val id: Int,
    @SerializedName("guid")
    val guid: String?,
    @SerializedName("imageurl")
    val imageUrl: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("body")
    val body: String?,
    @SerializedName("source_info")
    val sourceInfo: SourceInfo?
) : Serializable
