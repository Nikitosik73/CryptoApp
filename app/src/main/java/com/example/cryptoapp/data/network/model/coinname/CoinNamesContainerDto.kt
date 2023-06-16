package com.example.cryptoapp.data.network.model.coinname

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinNamesContainerDto(
    @SerializedName("CoinInfo")
    @Expose
    val coinName: CoinNameDto? = null
)
