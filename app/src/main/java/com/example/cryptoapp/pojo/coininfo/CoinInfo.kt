package com.example.cryptoapp.pojo.coininfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfo(
    @SerializedName("Name")
    @Expose
    private val name: String? = null
)
