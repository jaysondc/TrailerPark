package com.shakeup.trailerpark.features.nowplaying

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.shakeup.trailerpark.R
import com.shakeup.trailerpark.commons.NowPlaying
import com.shakeup.trailerpark.commons.RxBaseActivity
import com.shakeup.trailerpark.features.nowplaying.adapters.NowPlayingAdapter
import kotlinx.android.synthetic.main.activity_now_playing.*

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
        nowPlaying = movieManager.getNowPlaying()
        (recycler_now_playing.adapter as NowPlayingAdapter).addMovies(movieManager.getNowPlaying().movies)
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
