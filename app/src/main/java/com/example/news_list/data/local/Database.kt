package com.example.news_list.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.news_list.data.entities.ArticlesItem

@Database(entities = arrayOf(ArticlesItem::class), version = 1)
abstract class Database : RoomDatabase(){
    abstract fun newsDao() : NewsDao
}