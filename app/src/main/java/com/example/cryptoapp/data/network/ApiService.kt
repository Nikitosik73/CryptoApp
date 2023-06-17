package com.example.cryptoapp.data.network

import com.example.cryptoapp.data.network.model.coinname.CoinNamesListDto
import com.example.cryptoapp.data.network.model.news.NewsResponse
import com.example.cryptoapp.data.network.model.coininfo.CoinInfoJsonContainerDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top/totalvolfull")
    suspend fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "7180c5be373263271be28be7dffa06f6d9b3e8f5129d7904cc93f5b1c423a9ea",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSum: String = CURRENCY
    ): CoinNamesListDto

    @GET("pricemultifull")
    suspend fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "7180c5be373263271be28be7dffa06f6d9b3e8f5129d7904cc93f5b1c423a9ea",
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY
    ): CoinInfoJsonContainerDto

    @GET("v2/news/")
    fun getNewsCoin(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "7180c5be373263271be28be7dffa06f6d9b3e8f5129d7904cc93f5b1c423a9ea",
        @Query(LANGUAGE) language: String = LANGUAGE
    ): NewsResponse

    companion object {
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"

        private const val CURRENCY = "USD"

        private const val LANGUAGE = "EN"
    }
}