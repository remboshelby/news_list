package com.example.news_list.common

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    fun pushFragment(fragment: BaseFragment, shoudlAddToBackStack: Boolean) {
        if (!supportFragmentManager.popBackStackImmediate(fragment.javaClass.simpleName, 0)) {
            if (shoudlAddToBackStack)
                supportFragmentManager.beginTransaction()
                    .replace(containerResId(), fragment, fragment.javaClass.simpleName)
                    .addToBackStack(fragment.javaClass.simpleName)
                    .commit()
            else
                supportFragmentManager.beginTransaction()
                    .replace(containerResId(), fragment, fragment.javaClass.simpleName)
                    .commit()
        }
    }


    protected open fun containerResId(): Int {
        return 0
    }

    fun removeFragment(fragment: BaseFragment) {
        supportFragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss()
    }

    fun removePreviousFragment(fragmentName: String) {
        val fragments = supportFragmentManager.fragments
        for (i in fragments.indices) {
            val fragment = fragments[i]
            if (fragment.javaClass.simpleName != fragmentName) {
                removeFragment(fragment as BaseFragment)
                break
            }
        }
    }

    fun clear() {
        val backStackEntryCount = supportFragmentManager.backStackEntryCount
        for (i in 0 until backStackEntryCount) {
            supportFragmentManager.popBackStack()
        }
    }

}