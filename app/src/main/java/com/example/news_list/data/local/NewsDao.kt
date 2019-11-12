package com.example.news_list.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.news_list.data.entities.ArticlesItem
import io.reactivex.Single

@Dao
interface NewsDao{
    @Query("select * from news_table")
    fun queryAllNews() : Single<List<ArticlesItem>>

    @Query("select * from news_table limit :limit offset :offset")
    fun queryNewsWithOffset(limit:Int, offset:Int) : Single<List<ArticlesItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNew(articlesItem: ArticlesItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNews(articlesItem: List<ArticlesItem>)
}