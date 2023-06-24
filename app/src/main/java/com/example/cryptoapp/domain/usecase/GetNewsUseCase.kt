package com.example.cryptoapp.domain.usecase

import com.example.cryptoapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke() = repository.getNews()
}