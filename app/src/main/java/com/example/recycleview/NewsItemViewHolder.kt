package com.example.recycleview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTv: TextView = itemView.findViewById(R.id.title)
    private val subtitleTv: TextView = itemView.findViewById(R.id.subtitle)
    private val imageView: ImageView = itemView.findViewById(R.id.imageView)

    fun bind(item: NewsItem, listener: NewsItemAdapter.NewsClickListener) {
        titleTv.text = item.title
        subtitleTv.text = item.subtitle
        imageView.setBackgroundColor(item.color)
        imageView.setOnClickListener{
            listener.onFavoriteClick(
                item,
                adapterPosition - 1
            )
        }
        itemView.setOnClickListener{
            listener.onNewsClick(
                item,
                adapterPosition - 1
            )
        }

    }
}