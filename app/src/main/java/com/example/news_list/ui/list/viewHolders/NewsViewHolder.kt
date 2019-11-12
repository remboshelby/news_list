package com.example.news_list.ui.list.viewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.news_list.R
import com.example.news_list.data.entities.ArticlesItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_list_item.view.*

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var newsAuthor = itemView.findViewById<TextView>(R.id.news_author)
    var newsTitle = itemView.findViewById<TextView>(R.id.news_title)


    fun bind(articlesItem: ArticlesItem?) {
        if (articlesItem != null) {
            newsAuthor.text = articlesItem.author
            newsTitle.text = articlesItem.title
            if (!articlesItem.urlToImage.isNullOrEmpty())
                Picasso.get().load(articlesItem.urlToImage)
                    .resize(200,200)
                    .centerCrop()
                    .error(R.drawable.no_data)
                    .into(itemView.img_news_banner)
            else {
                Picasso.get().load(R.drawable.no_data).into(itemView.img_news_banner)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): NewsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.news_list_item, parent, false)
            return NewsViewHolder(view)
        }
    }
}
