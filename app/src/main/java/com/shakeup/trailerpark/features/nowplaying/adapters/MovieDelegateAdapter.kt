package com.shakeup.trailerpark.features.nowplaying.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.shakeup.trailerpark.R
import com.shakeup.trailerpark.commons.MovieItem
import com.shakeup.trailerpark.commons.adapters.ViewType
import com.shakeup.trailerpark.commons.adapters.ViewTypeDelegateAdapter
import com.shakeup.trailerpark.commons.inflate
import com.shakeup.trailerpark.commons.loadImg
import kotlinx.android.synthetic.main.listitem_movies.view.*

/**
 * Created by Jayson on 9/14/2017.
 */
class MovieDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = MovieViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as MovieViewHolder
        holder.bind(item as MovieItem)
    }

    class MovieViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.listitem_movies)) {
        fun bind(item: MovieItem) = with(itemView) {
            img_thumbnail.loadImg(item.poster_path)
            title.text = item.title
            overview.text = item.overview
        }
    }
}