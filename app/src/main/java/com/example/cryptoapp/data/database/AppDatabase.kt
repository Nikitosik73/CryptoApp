package com.example.cryptoapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cryptoapp.data.database.model.CoinInfoDbModel
import com.example.cryptoapp.data.database.model.NewsInfoDbModel

@Database(entities = [CoinInfoDbModel::class, NewsInfoDbModel::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun coinInfoDao(): CoinInfoDao
    abstract fun newsInfoDao(): NewsInfoDao

    companion object {
        private const val NAME_DB = "main.db"
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                instance?.let { return it }
                val db = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    NAME_DB
                ).fallbackToDestructiveMigration().build()
                instance = db
                return db
            }
        }
    }
}