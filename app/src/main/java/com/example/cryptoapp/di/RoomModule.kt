package com.example.cryptoapp.di

import android.app.Application
import com.example.cryptoapp.data.database.AppDatabase
import com.example.cryptoapp.data.database.CoinInfoDao
import com.example.cryptoapp.data.database.NewsInfoDao
import com.example.cryptoapp.di.annotation.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class RoomModule {

    @Provides
    @ApplicationScope
    fun provideRoomDatabase(
        application: Application
    ): AppDatabase = AppDatabase.getInstance(application)

    @Provides
    @ApplicationScope
    fun provideCoinDao(
        database: AppDatabase
    ): CoinInfoDao = database.coinInfoDao()

    @Provides
    @ApplicationScope
    fun provideNewsApi(
        database: AppDatabase
    ): NewsInfoDao = database.newsInfoDao()
}