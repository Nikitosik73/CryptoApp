package com.example.cryptoapp.data.mapper

import com.example.cryptoapp.data.database.model.NewsInfoDbModel
import com.example.cryptoapp.data.database.model.TitleNewsDbModel
import com.example.cryptoapp.data.network.model.news.NewsContainerDto
import com.example.cryptoapp.data.network.model.news.NewsInfoDto
import com.example.cryptoapp.data.network.model.news.TitleNewsDto
import com.example.cryptoapp.domain.entity.news.NewsInfo
import com.example.cryptoapp.domain.entity.news.TitleNews
import javax.inject.Inject

class NewsMapper @Inject constructor() {

    fun mapDtoToDbModel(dto: NewsInfoDto) = NewsInfoDbModel(
        id = dto.id,
        guid = dto.guid,
        imageUrl = dto.imageUrl,
        title = dto.title,
        body = dto.body,
//        titleNews = TitleNewsDbModel(name = dto.titleNews.name)
    )

    fun mapDbModelToEntity(dbModel: NewsInfoDbModel) = NewsInfo(
        id = dbModel.id,
        guid = dbModel.guid,
        imageUrl = dbModel.imageUrl,
        title = dbModel.title,
        body = dbModel.body,
//        titleNews = TitleNews(name = dbModel.titleNews.name)
    )

    fun mapNewsContainerToListNews(news: NewsContainerDto): List<NewsInfoDto> {
        return news.data ?: throw RuntimeException("Empty List")
    }
}