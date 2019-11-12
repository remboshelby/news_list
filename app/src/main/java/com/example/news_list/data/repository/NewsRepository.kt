package com.example.news_list.data.repository

import android.util.Log
import com.example.news_list.data.entities.ArticlesItem
import com.example.news_list.data.entities.NewsEntity
import com.example.news_list.data.local.NewsDao
import com.example.news_list.data.remote.NewsApi
import com.example.news_list.utils.Constants
import com.example.news_list.utils.Utils
import com.example.news_list.utils.Utils.Companion.PAGE_SIZE
import io.reactivex.Observable
import javax.inject.Inject

class NewsRepository @Inject constructor(
    val apiNews: NewsApi,
    val newsDao: NewsDao,
    val utils: Utils
) {
    fun getNews(page: Int): Observable<List<ArticlesItem>> {
        val hasConnection = utils.isConnectedToInternet()
        var observableFromApi: Observable<List<ArticlesItem>>? = null
        if (hasConnection) {
            observableFromApi = getNewsListFromApi(page);
        }
        val observableFromDb = getNewsFromDb(page)
        return if (hasConnection) Observable.concatArrayEager(observableFromApi, observableFromDb)
        else getNewsFromDb(page)
    }

    fun getNewsListFromApi(page: Int): Observable<List<ArticlesItem>> {
        return apiNews.getNews(
            Constants.q,
            Constants.from,
            Constants.sortBy,
            Constants.apiKey,
            page
        )
            .map { t: NewsEntity ->
                var articles = t.articles
                newsDao.insertAllNews(articles)
                articles
            }
    }

    fun getNewsFromDb(page: Int): Observable<List<ArticlesItem>> {
        return newsDao.queryNewsWithOffset(page * PAGE_SIZE, (page - 1) * PAGE_SIZE)
            .toObservable().doOnNext {
                Log.e("REPOSITORY DB *** ", it.size.toString())
            }
    }
}