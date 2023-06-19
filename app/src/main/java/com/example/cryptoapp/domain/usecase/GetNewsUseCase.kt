package com.example.cryptoapp.domain.usecase

import com.example.cryptoapp.domain.repository.NewsRepository

class GetNewsUseCase(private val repository: NewsRepository) {

    operator fun invoke() = repository.getNews()
}