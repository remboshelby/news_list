package com.example.news_list.data.remote

import com.example.news_list.data.entities.ArticlesItem
import com.example.news_list.data.entities.NewsEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi{
    @GET("everything")
    fun getNews(@Query("q") q: String,
                @Query("from") from: String,
                @Query("sortBy") sortBy: String,
                @Query("apiKey") apiKey: String,
                @Query("page") page : Int) : Observable<NewsEntity>
}