package com.example.cryptoapp.data.mapper

import com.example.cryptoapp.data.database.model.NewsInfoDbModel
import com.example.cryptoapp.data.network.model.news.NewsContainerDto
import com.example.cryptoapp.data.network.model.news.NewsInfoDto
import com.example.cryptoapp.data.network.model.news.TitleNewsDto
import com.example.cryptoapp.domain.entity.news.NewsInfo
import javax.inject.Inject

class NewsMapper @Inject constructor() {

    fun mapDtoToDbModel(dto: NewsInfoDto) = NewsInfoDbModel(
        id = dto.id,
        guid = dto.guid,
        imageUrl = dto.imageUrl,
        title = dto.title,
        body = dto.body,
        nameNews = mapDtoTitleNewsToString(TitleNewsDto(dto.titleNews.name))
    )

    private fun mapDtoTitleNewsToString(dto: TitleNewsDto): String {
        return dto.name
    }

    fun mapDbModelToEntity(dbModel: NewsInfoDbModel) = NewsInfo(
        id = dbModel.id,
        guid = dbModel.guid,
        imageUrl = dbModel.imageUrl,
        title = dbModel.title,
        body = dbModel.body,
        titleNews = dbModel.nameNews
    )

    fun mapNewsContainerToListNews(news: NewsContainerDto): List<NewsInfoDto> {
        return news.data ?: throw RuntimeException("Empty List")
    }
}