package com.example.cryptoapp.domain.usecase

import com.example.cryptoapp.domain.repository.NewsRepository

class LoadNewsUseCase(private val repository: NewsRepository) {

    suspend operator fun invoke() = repository.loadNews()
}