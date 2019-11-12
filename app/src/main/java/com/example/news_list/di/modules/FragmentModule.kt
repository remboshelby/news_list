package com.example.news_list.di.modules

import androidx.fragment.app.Fragment
import com.example.news_list.ui.list.NewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule{
    @ContributesAndroidInjector
    abstract fun contributeMainFragment() : NewsFragment
}