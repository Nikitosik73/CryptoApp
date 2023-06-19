package com.example.cryptoapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptoapp.data.database.model.NewsInfoDbModel

@Dao
interface NewsInfoDao {

    @Query("select * from news")
    fun getAllNews(): LiveData<List<NewsInfoDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsList(listNews: List<NewsInfoDbModel>)
}