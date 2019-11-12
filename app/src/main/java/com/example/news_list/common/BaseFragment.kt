package com.example.news_list.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    fun getRoot(): BaseActivity {
        return activity as BaseActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflate(inflater, container)
    }

    protected abstract fun inflate(inflater: LayoutInflater, container: ViewGroup?): View

    fun pushFragmentIntoFragment(fragment: BaseFragment) {
        if (!childFragmentManager.popBackStackImmediate(fragment.javaClass.simpleName, 0)) {
            childFragmentManager.beginTransaction()
                .replace(containerResId(), fragment, fragment.javaClass.simpleName)
                .addToBackStack(fragment.javaClass.simpleName)
                .commit()
        }
    }

    protected fun containerResId(): Int {
        return 0
    }
}