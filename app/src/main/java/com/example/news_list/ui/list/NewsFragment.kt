package com.example.news_list.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news_list.R
import com.example.news_list.common.BaseFragment
import com.example.news_list.data.entities.ArticlesItem
import com.example.news_list.ui.news_detail_watcher.NewsDetailFragment
import com.example.news_list.utils.State
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.news_fragment.*
import javax.inject.Inject

class NewsFragment : BaseFragment(), NewsAdapter.ClickListener {
    override fun itemClickListener(articlesItem: ArticlesItem?) {
        if (articlesItem != null) {
            getRoot().pushFragment(NewsDetailFragment.newInstance(articlesItem.url), true)
        }
    }


    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(R.layout.news_fragment, container, false);
    }

    @Inject
    lateinit var newsViewModelFactory: NewsViewModelFactory

    lateinit var newsAdapter : NewsAdapter
    lateinit var newsViewModel: NewsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel = ViewModelProviders.of(getRoot(), newsViewModelFactory)
            .get(NewsViewModel::class.java)
        
        initAdapter()
        initState()


    }
    private fun initAdapter(){
        newsAdapter = NewsAdapter(this){ newsViewModel.retry()}
        recycler.layoutManager = LinearLayoutManager(getRoot(), RecyclerView.VERTICAL, false)
        recycler.adapter = newsAdapter
        newsViewModel.newsResult.observe(this, Observer {
            newsAdapter.submitList(it)
        })
    }
    private fun initState(){
        txt_error.setOnClickListener{ newsViewModel.retry()}
        newsViewModel.getState().observe(this, Observer { state ->
            progressBar.visibility = if (newsViewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if (newsViewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE
            if (!newsViewModel.listIsEmpty()){
                newsAdapter.setState(state ?: State.DONE)
            }
        })
    }
}