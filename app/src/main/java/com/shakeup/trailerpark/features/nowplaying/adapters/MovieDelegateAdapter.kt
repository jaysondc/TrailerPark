package com.shakeup.trailerpark.features.nowplaying.adapters

import android.content.res.Configuration
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.shakeup.trailerpark.R
import com.shakeup.trailerpark.commons.*
import com.shakeup.trailerpark.commons.adapters.ViewType
import com.shakeup.trailerpark.commons.adapters.ViewTypeDelegateAdapter
import com.shakeup.trailerpark.features.nowplaying.MovieManager
import kotlinx.android.synthetic.main.listitem_movies.view.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Jayson on 9/14/2017.
 * Adapter for handling Movies inside the RecyclerView
 */
class MovieDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = MovieViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as MovieViewHolder
        holder.bind(item as MovieItem)
    }

    class MovieViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.listitem_movies)) {

        private val mMovieManager by lazy { MovieManager }
        private var mTrailers : Trailers? = null

        fun bind(item: MovieItem) = with(itemView) {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_thumbnail.loadImg(item.getPosterPath185())
            } else {
                img_thumbnail.loadImg(item.getBackdropPath780())
            }
            title.text = item.title
            overview.text = item.overview

            requestTrailers(item.id)
        }

        private fun addTrailerViews() {

        }

        /**
         * Creates a subscription object, specifying the request to run on the IO thread
         * Observed actions will be handled on the Main thread
         * Success will assign the movies to the adapter,
         * Error will show a snackbar error message
         */
        private fun requestTrailers(movieId: Int) {
            val subscription = mMovieManager.getTrailers(movieId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe (
                            // onNext(retrievedTrailers)
                            { retrievedTrailers ->
                                mTrailers = retrievedTrailers
                                addTrailerViews()
                            },
                            // onError(e)
                            { e ->
                                Snackbar.make(itemView, e.message ?: "", Snackbar.LENGTH_LONG).show()
                            }
                    )
            RxBaseActivity.RxSubscriptions.subscriptions.add(subscription)
        }
    }
}