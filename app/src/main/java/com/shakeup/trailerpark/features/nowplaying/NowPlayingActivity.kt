package com.shakeup.trailerpark.features.nowplaying

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import com.shakeup.trailerpark.R
import com.shakeup.trailerpark.commons.NowPlaying
import com.shakeup.trailerpark.commons.RxBaseActivity
import com.shakeup.trailerpark.features.nowplaying.adapters.NowPlayingAdapter
import kotlinx.android.synthetic.main.activity_now_playing.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class NowPlayingActivity : RxBaseActivity() {

    private var nowPlaying: NowPlaying? = null
    private val movieManager by lazy { MovieManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_now_playing)


        recycler_now_playing.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
        }

        initAdapter()

        requestNowPlaying()
    }

    private fun requestNowPlaying() {
        /**
         * Creates a subscription object, specifying the request to run on the IO thread
         * Observed actions will be handled on the Main thread
         * Success will assign the movies to the adapter,
         * Error will show a snackbar error message
         */
        val subscription = movieManager.getNowPlaying(nowPlaying?.page ?: 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        { retrievedMovies ->
                            nowPlaying = retrievedMovies
                            (recycler_now_playing.adapter as NowPlayingAdapter).addMovies(retrievedMovies.movies)
                        },
                        { e ->
                            Snackbar.make(recycler_now_playing, e.message ?: "", Snackbar.LENGTH_LONG).show()
                        }
                )
        subscriptions.add(subscription)
    }

    /**
     * Initialize the adapter
     */
    private fun initAdapter() {
        if (recycler_now_playing.adapter == null) {
            recycler_now_playing.adapter = NowPlayingAdapter()
        }
    }
}
