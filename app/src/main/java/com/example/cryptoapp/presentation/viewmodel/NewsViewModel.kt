package com.example.cryptoapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.domain.usecase.GetNewsUseCase
import com.example.cryptoapp.domain.usecase.LoadNewsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val loadNewsUseCase: LoadNewsUseCase
) : ViewModel() {

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