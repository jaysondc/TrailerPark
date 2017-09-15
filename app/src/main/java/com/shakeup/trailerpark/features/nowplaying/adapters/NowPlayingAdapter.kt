package com.shakeup.trailerpark.features.nowplaying.adapters

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.shakeup.trailerpark.commons.MovieItem
import com.shakeup.trailerpark.commons.adapters.AdapterConstants
import com.shakeup.trailerpark.commons.adapters.ViewType
import com.shakeup.trailerpark.commons.adapters.ViewTypeDelegateAdapter

/**
 * Created by Jayson on 9/14/2017.
 *
 * Adapter for the movies RecyclerView. Handles delegate adapters and uses the appropriate one
 * based on the ViewType of the item
 */
class NowPlayingAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.MOVIE_NORMAL, MovieDelegateAdapter())
        delegateAdapters.put(AdapterConstants.MOVIE_HIGHLIGHTED, MovieDelegateAdapter()) //TODO: Add a highlighted movie adapter
        items = ArrayList()
        //items.add(loadingItem)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    // Gets the appropriate DelegateAdapter and calls its onCreateViewHolder method
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }

    // Gets the appropriate DelegateAdapter and calls its onBindViewHolder method
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position])
    }

    override fun getItemViewType(position: Int): Int = items[position].getViewType()

    /**
     * Add a list of movies to the adapter. Use this method to append movies to the existing list.
     */
    fun addMovies(movies: List<MovieItem>) {
        // first remove loading and notify
//        val initPosition = items.size // -1 TODO when loading item works
//        items.removeAt(initPosition)
//        notifyItemRemoved(initPosition)

        // insert news and the loading at the end of the list
        items.addAll(movies)
        //items.add(loadingItem)
        notifyItemRangeChanged(0, items.size /* +1 TODO plus loading item */)
    }

    /**
     * Clear the adapter and add new movies
     */
    fun clearAndAddMovies(movies: List<MovieItem>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(movies)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    /**
     * Gets the adapter contents excluding the loadingItem
     */
    fun getMovies(): List<MovieItem> {
        return items
                .filter { it.getViewType() != AdapterConstants.LOADING }
                .map { it as MovieItem }
    }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex
}