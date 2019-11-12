package com.example.news_list.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.news_list.data.entities.ArticlesItem
import com.example.news_list.data.repository.NewsRepository
import com.example.news_list.ui.list.paging.NewsDataSource
import com.example.news_list.ui.list.paging.NewsDataSourceFactory
import com.example.news_list.utils.State
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    newsRepository: NewsRepository) : ViewModel(){

    val newsResult: LiveData<PagedList<ArticlesItem>>
    private val compositeDisposable = CompositeDisposable()

    private var pageSize = 5;

    private var newsDataSourceFactory: NewsDataSourceFactory

    init {
        newsDataSourceFactory = NewsDataSourceFactory(compositeDisposable, newsRepository)
        var config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize*2)
            .setEnablePlaceholders(false)
            .build()
        newsResult = LivePagedListBuilder(newsDataSourceFactory, config).build()
    }
    fun retry(){
        newsDataSourceFactory.newsDataSourceLiveData.value?.retry()
    }
    fun listIsEmpty(): Boolean {
        return newsResult.value?.isEmpty() ?: true
    }
    fun getState(): LiveData<State> = Transformations.switchMap<NewsDataSource,
            State>(newsDataSourceFactory.newsDataSourceLiveData, NewsDataSource::state)
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
