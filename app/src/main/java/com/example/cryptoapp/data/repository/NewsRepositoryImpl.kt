package com.example.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.example.cryptoapp.data.database.AppDatabase
import com.example.cryptoapp.data.mapper.NewsMapper
import com.example.cryptoapp.data.network.ApiFactory
import com.example.cryptoapp.domain.entity.news.NewsInfo
import com.example.cryptoapp.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsRepositoryImpl(
    private val application: Application
) : NewsRepository {

    private val newsDao = AppDatabase.getInstance(application).newsInfoDao()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val apiService = ApiFactory.apiService
    private val mapper = NewsMapper()

    override fun getNews(): LiveData<List<NewsInfo>> {
        val news = newsDao.getAllNews()
        return news.switchMap { listNewsDbModels ->
            MutableLiveData<List<NewsInfo>>().apply {
                value = listNewsDbModels.map { dbModel ->
                    mapper.mapDbModelToEntity(dbModel)
                }
            }
        }
    }

    override suspend fun loadNews() {
        coroutineScope.launch {
            val newsContainer = apiService.getNewsCoin()
            val listNewsDto = mapper.mapNewsContainerToListNews(newsContainer)
            val listDbModel = listNewsDto.map { dto ->
                mapper.mapDtoToDbModel(dto)
            }
            newsDao.insertNewsList(listDbModel)
        }
    }
}