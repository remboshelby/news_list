package com.example.news_list.di.modules

import com.example.news_list.ui.NewsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeNewsActivity() : NewsActivity

}