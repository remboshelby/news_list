package com.example.news_list.ui.list.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.news_list.data.entities.ArticlesItem
import com.example.news_list.data.repository.NewsRepository
import io.reactivex.disposables.CompositeDisposable

class NewsDataSourceFactory (private val compositeDisposable: CompositeDisposable,
                             private val newsRepository: NewsRepository)
    :DataSource.Factory<Int, ArticlesItem>(){

    val newsDataSourceLiveData = MutableLiveData<NewsDataSource>()

    override fun create(): DataSource<Int, ArticlesItem> {
        val newDataSource = NewsDataSource(newsRepository, compositeDisposable)
        newsDataSourceLiveData.postValue(newDataSource)
        return newDataSource
    }

}