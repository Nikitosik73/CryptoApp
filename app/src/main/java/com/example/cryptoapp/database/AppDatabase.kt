package com.example.cryptoapp.database

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class AppDatabase : RoomDatabase() {

    abstract fun coinPriceInfoDao(): CoinPriceInfoDao

    companion object {
        private const val NAME_DB = "main.db"
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        fun getInstance(application: Application): AppDatabase {
            synchronized(LOCK) {
                instance?.let { return it }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    NAME_DB
                ).build()
                instance = db
                return db
            }
        }
    }
}