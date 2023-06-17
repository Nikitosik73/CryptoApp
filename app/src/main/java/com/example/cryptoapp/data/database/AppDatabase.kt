package com.example.cryptoapp.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cryptoapp.data.database.model.CoinInfoDbModel

@Database(entities = [CoinInfoDbModel::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun coinInfoDao(): CoinInfoDao

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
                ).fallbackToDestructiveMigration().build()
                instance = db
                return db
            }
        }
    }
}