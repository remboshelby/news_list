package com.example.news_list.ui.list

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.news_list.data.entities.ArticlesItem
import com.example.news_list.ui.list.viewHolders.ListFooterViewHolder
import com.example.news_list.ui.list.viewHolders.NewsViewHolder
import com.example.news_list.utils.State

class NewsAdapter(clickListener : ClickListener, private val retry: () -> Unit) :
    PagedListAdapter<ArticlesItem, RecyclerView.ViewHolder>(NewsDiffCallback)
{

    interface ClickListener{
        fun itemClickListener(articlesItem: ArticlesItem?)
    }


    private val DATA_VIEW_TYPE = 1
    private val FOOTER_VIEW_TYPE = 2

    private var state = State.LOADING
    var clickListener : ClickListener

    init {
        this.clickListener = clickListener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == DATA_VIEW_TYPE) NewsViewHolder.create(parent) else ListFooterViewHolder.create(
            retry,
            parent
        )
    }

    companion object {
        val NewsDiffCallback = object : DiffUtil.ItemCallback<ArticlesItem>() {
            override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem.url == newItem.url
            }

        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == State.LOADING || state == State.ERROR)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) DATA_VIEW_TYPE else FOOTER_VIEW_TYPE
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_VIEW_TYPE) {
            (holder as NewsViewHolder).bind(getItem(position))
            holder.itemView.setOnClickListener {
                clickListener.itemClickListener(getItem(position));
            }
        }
        else (holder as ListFooterViewHolder).bind(state)
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }
}
