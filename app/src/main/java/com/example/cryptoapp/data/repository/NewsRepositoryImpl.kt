package com.example.cryptoapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.example.cryptoapp.data.database.NewsInfoDao
import com.example.cryptoapp.data.mapper.NewsMapper
import com.example.cryptoapp.data.network.NewsApi
import com.example.cryptoapp.domain.entity.news.NewsInfo
import com.example.cryptoapp.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsDao: NewsInfoDao,
    private val mapper: NewsMapper,
    private val newsApi: NewsApi,
    private val coroutineScope: CoroutineScope
) : NewsRepository {

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
            val newsContainer = newsApi.getNewsCoin()
            val listNewsDto = mapper.mapNewsContainerToListNews(newsContainer)
            val listDbModel = listNewsDto.map { dto ->
                mapper.mapDtoToDbModel(dto)
            }
            newsDao.insertNewsList(listDbModel)
        }
    }
}