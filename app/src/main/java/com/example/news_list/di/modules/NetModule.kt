package com.example.news_list.di.modules

import com.example.news_list.data.remote.NewsApi
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetModule (private var baseUrl : String){
    @Provides
    @Singleton
    fun providesOkHttpClient() : OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun providesMoshi() : Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi) : Retrofit{
        return Retrofit.Builder().client(okHttpClient).baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun providesApiInterface(retrofit: Retrofit) : NewsApi =retrofit.create(
        NewsApi::class.java)
}