package com.example.cryptoapp.di

import android.app.Application
import com.example.cryptoapp.data.database.AppDatabase
import com.example.cryptoapp.data.database.CoinInfoDao
import com.example.cryptoapp.data.database.NewsInfoDao
import com.example.cryptoapp.data.network.ApiFactory
import com.example.cryptoapp.data.network.ApiService
import com.example.cryptoapp.data.repository.CoinRepositoryImpl
import com.example.cryptoapp.data.repository.NewsRepositoryImpl
import com.example.cryptoapp.di.annotation.ApplicationScope
import com.example.cryptoapp.domain.repository.CoinRepository
import com.example.cryptoapp.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(
        impl: CoinRepositoryImpl
    ): CoinRepository

    @Binds
    fun bindNewsRepository(
        impl: NewsRepositoryImpl
    ): NewsRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideCoinDao(
            application: Application
        ): CoinInfoDao {
            return AppDatabase.getInstance(application).coinInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideNewsDao(
            application: Application
        ): NewsInfoDao {
            return AppDatabase.getInstance(application).newsInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

        @Provides
        fun provideCoroutineScope(): CoroutineScope {
            return CoroutineScope(Dispatchers.IO)
        }
    }
}