package com.example.cryptoapp.domain.usecase

import com.example.cryptoapp.domain.repository.NewsRepository
import javax.inject.Inject

class LoadNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    suspend operator fun invoke() = repository.loadNews()
}