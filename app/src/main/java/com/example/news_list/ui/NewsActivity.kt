package com.example.news_list.ui

import android.os.Bundle
import com.example.news_list.R
import com.example.news_list.common.BaseActivity
import com.example.news_list.ui.list.NewsFragment

class NewsActivity : BaseActivity() {

    override fun containerResId(): Int {
        return R.id.activity_container
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)

        pushFragment(NewsFragment(),false)


    }
}
