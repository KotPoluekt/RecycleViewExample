package com.example.recycleview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "NewsItemAdapter"
private const val HEADER_VIEW_TYPE = 0
private const val ITEM_VIEW_TYPE = 1

class NewsItemAdapter(
    private val items: List<NewsItem>,
    private val listener: NewsClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    interface NewsClickListener {
        fun onNewsClick(newsItem: NewsItem, position: Int)
        fun onFavoriteClick(newsItem: NewsItem, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d(TAG, "onCreateViewHolder $viewType")

        val inflater = LayoutInflater.from(parent.context)

        return when(viewType) {
            HEADER_VIEW_TYPE -> HeaderItemViewHolder(
                inflater.inflate(
                    R.layout.item_header_news,
                    parent,
                    false
                )
            )
            else -> NewsItemViewHolder(inflater.inflate(
                    R.layout.item_news,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: $position")

        when (holder) {
            is NewsItemViewHolder -> {
                holder.bind(items[position - 1], listener)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) HEADER_VIEW_TYPE else ITEM_VIEW_TYPE
    }
}