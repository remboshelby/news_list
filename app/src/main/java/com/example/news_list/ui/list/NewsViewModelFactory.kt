package com.example.news_list.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject

class NewsViewModelFactory @Inject constructor(
    private val newsViewModel: NewsViewModel) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)){
            return newsViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}