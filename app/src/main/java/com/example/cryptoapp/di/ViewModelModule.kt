package com.example.cryptoapp.di

import androidx.lifecycle.ViewModel
import com.example.cryptoapp.di.annotation.ViewModelKey
import com.example.cryptoapp.presentation.viewmodel.CoinViewModel
import com.example.cryptoapp.presentation.viewmodel.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    @Binds
    fun bindCoinViewModel(
        impl: CoinViewModel
    ): ViewModel

    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    @Binds
    fun bindNewsViewModel(
        impl: NewsViewModel
    ): ViewModel
}