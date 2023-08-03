package com.example.cryptoapp.data.network

import com.example.cryptoapp.data.network.model.news.NewsContainerDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/news/")
    suspend fun getNewsCoin(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "7180c5be373263271be28be7dffa06f6d9b3e8f5129d7904cc93f5b1c423a9ea",
        @Query(LANGUAGE) language: String = LANGUAGE
    ): NewsContainerDto

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val LANGUAGE = "EN"
    }
}