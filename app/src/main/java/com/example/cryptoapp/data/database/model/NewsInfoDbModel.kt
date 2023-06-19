package com.example.cryptoapp.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "news")
data class NewsInfoDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val guid: String,
    val imageUrl: String,
    val title: String,
    val body: String,
//    val titleNews: TitleNewsDbModel
): Serializable