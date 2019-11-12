package com.example.news_list.ui.list.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.news_list.data.entities.ArticlesItem
import com.example.news_list.data.repository.NewsRepository
import com.example.news_list.utils.State
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class NewsDataSource(
    private val newsRepository: NewsRepository,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, ArticlesItem>() {

    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ArticlesItem>
    ) {
        updateState(State.LOADING)
        compositeDisposable.add(
            newsRepository.getNews(1).subscribe({ response ->
                updateState(State.DONE)
                callback.onResult(
                    response,
                    null,
                    2
                )
            },
                {
                    updateState(State.ERROR)
                    setRetry(Action { loadInitial(params, callback) })
                }
            )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ArticlesItem>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            newsRepository.getNews(params.key)
                .subscribe({ response ->
                    updateState(State.DONE)
                    callback.onResult(response, params.key + 1)
                },
                    {
                        updateState(State.ERROR)
                        setRetry(Action { loadAfter(params, callback) })
                    })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ArticlesItem>) {
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(
                retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            )
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }
}