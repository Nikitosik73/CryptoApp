package com.example.cryptoapp.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val BASE_URl = "https://min-api.cryptocompare.com/data/"
    const val BASE_IMAGE_URl = "https://cryptocompare.com"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URl)
        .build()

    val apiService = retrofit.create(ApiService::class.java)
}