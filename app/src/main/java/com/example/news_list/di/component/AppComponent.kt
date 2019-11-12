package com.example.news_list.di.component

import com.example.news_list.NewsApplication
import com.example.news_list.common.BaseFragment
import com.example.news_list.di.modules.AppModule
import com.example.news_list.di.modules.ActivityModule
import com.example.news_list.di.modules.FragmentModule
import com.example.news_list.di.modules.NetModule
import com.example.news_list.ui.list.NewsFragment
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        AppModule::class,
        NetModule::class
    )
)
interface AppComponent{
    fun inject(app: NewsApplication)
}