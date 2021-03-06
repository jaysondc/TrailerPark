package com.shakeup.trailerpark.features.nowplaying.adapters

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.shakeup.trailerpark.R
import com.shakeup.trailerpark.commons.*
import com.shakeup.trailerpark.commons.adapters.ViewType
import com.shakeup.trailerpark.commons.adapters.ViewTypeDelegateAdapter
import com.shakeup.trailerpark.features.nowplaying.MovieManager
import kotlinx.android.synthetic.main.cardview_trailer.view.*
import kotlinx.android.synthetic.main.listitem_movies.view.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Jayson on 9/14/2017.
 * Adapter for handling Movies inside the RecyclerView
 */
class MovieDelegateAdapter : ViewTypeDelegateAdapter {
    private val mTrailerReycler = TrailerViewFactory()

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = MovieViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as MovieViewHolder
        holder.bind(item as MovieItem, mTrailerReycler)
    }

    class MovieViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.listitem_movies)) {

        private val mMovieManager by lazy { MovieManager }
        private var mTrailers: Trailers? = null
        private var mTrailerFactory: TrailerViewFactory? = null

        fun bind(item: MovieItem, trailerFactory: TrailerViewFactory) = with(itemView) {
            scrollview_movie_trailers.scrollTo(0,0)
            mTrailerFactory = trailerFactory

            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_thumbnail.loadImg(item.getPosterPath185())
            } else {
                img_thumbnail.loadImg(item.getBackdropPath780())
            }
            title.text = item.title
            overview.text = item.overview

            requestTrailers(item.id)
        }

        /**
         * Ensures there are corresponding views for each trailer a movie has. This method
         * uses the TrailerFactory to recycle trailers and improve performance
         */
        private fun addTrailerViews() {
            with(itemView.linearlayout_movie_trailers) {
                val numTrailers = mTrailers?.youtube?.size ?: 0
                if (numTrailers == 0) {
                    Log.d("LOG_TAG", "No trailers!")
                }

                // Remove all trailer views
                while (childCount > 1) {
                    mTrailerFactory?.recycleView(getChildAt(1))
                    removeViewAt(1)
                }

                // Add trailer views
                while (childCount < numTrailers + 1) {
                    addView(mTrailerFactory?.getView(parent as ViewGroup))
                }

                if (childCount != numTrailers + 1) Log.d("LOG_TAG", "Trailer count mismatch");
            }

            bindTrailers()
        }

        /**
         * Bind trailer data to trailer views
         */
        private fun bindTrailers() {
            with(itemView.linearlayout_movie_trailers) {
                val numTrailers = mTrailers?.youtube?.size ?: 0
                if (numTrailers > 0) {
                    for (i in 1 until childCount) {
                        val trailerView: View = getChildAt(i)
                        val trailer: TrailerItem? = mTrailers?.youtube?.get(i-1)

                        trailerView.trailer_title.text = trailer?.name ?: "No Name"
                        trailerView.video_preview.loadImg(trailer?.generatePreviewHQ() ?: "")

                        // Launch youtube video in the Youtube app on click
                        trailerView.setOnClickListener {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(trailer?.generateVideoUrl()))
                            context.startActivity(intent)
                        }
                    }
                }
            }
        }

        /**
         * Creates a subscription object, specifying the request to run on the IO thread
         * Observed actions will be handled on the Main thread
         * Success will assign the trailer to the mTrailers variable and init trailer view setup
         * Error will show a snackbar error message
         */
        private fun requestTrailers(movieId: Int) {
            val subscription = mMovieManager.getTrailers(movieId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
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