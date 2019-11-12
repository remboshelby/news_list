package com.example.news_list.di.modules

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.news_list.data.local.Database
import com.example.news_list.data.local.NewsDao
import com.example.news_list.ui.list.NewsViewModelFactory
import com.example.news_list.utils.Constants
import com.example.news_list.utils.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {
    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideNewsDatabase(app: Application): Database = Room.databaseBuilder(
        app,
        Database::class.java, Constants.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideNewsDao(database: Database): NewsDao =
        database.newsDao()

    @Provides
    @Singleton
    fun provideUtils(): Utils = Utils(app)

    @Provides
    @Singleton
    fun provideNewsViewModelFactory(factory: NewsViewModelFactory)
            : ViewModelProvider.Factory = factory
}