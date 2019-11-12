package com.example.news_list

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.example.news_list.di.component.DaggerAppComponent
import com.example.news_list.di.modules.AppModule
import com.example.news_list.di.modules.NetModule
import com.example.news_list.utils.Constants
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class NewsApplication : Application(), HasActivityInjector, HasSupportFragmentInjector  {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .netModule(NetModule(Constants.URL))
            .build()
            .inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
}