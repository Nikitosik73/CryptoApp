package com.example.cryptoapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.data.repository.NewsRepositoryImpl
import com.example.cryptoapp.domain.usecase.GetNewsUseCase
import com.example.cryptoapp.domain.usecase.LoadNewsUseCase
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NewsRepositoryImpl(application)

    private val getNewsUseCase = GetNewsUseCase(repository)
    private val loadNewsUseCase = LoadNewsUseCase(repository)

    val newsList = getNewsUseCase()

    init {
        loadNews()
    }

    private fun loadNews() {
        viewModelScope.launch {
            loadNewsUseCase()
        }
    }
}