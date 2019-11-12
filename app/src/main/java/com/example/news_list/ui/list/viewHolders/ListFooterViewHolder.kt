package com.example.news_list.ui.list.viewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news_list.R
import com.example.news_list.utils.State
import kotlinx.android.synthetic.main.item_list_footer.view.*

class ListFooterViewHolder (view : View) : RecyclerView.ViewHolder(view){
    fun bind(state: State?){
        itemView.footer_progress_bar.visibility = if (state == State.LOADING) VISIBLE else INVISIBLE
        itemView.footer_txt_error.visibility = if (state == State.ERROR) VISIBLE else INVISIBLE
    }
    companion object {
        fun create(retry: () -> Unit, parent: ViewGroup): ListFooterViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_footer, parent, false)
            view.footer_txt_error.setOnClickListener{retry()}
            return ListFooterViewHolder(view)
        }
    }
}