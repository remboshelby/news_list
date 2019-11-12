package com.example.news_list.ui.news_detail_watcher

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.news_list.R
import com.example.news_list.common.BaseFragment

class NewsDetailFragment :BaseFragment(){

    public lateinit var webView : WebView

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(R.layout.news_details,container, false)
    }
    companion object{
        private val ARG_NAME = "news_url"
        fun newInstance(uri: String):NewsDetailFragment{
            val args: Bundle = Bundle()
            args.putString(ARG_NAME, uri)
            var fragment = NewsDetailFragment()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = view.findViewById(R.id.web_view)
        webView.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView.loadUrl(arguments?.getString(ARG_NAME,"https://www.google.co.in/"))
    }
}