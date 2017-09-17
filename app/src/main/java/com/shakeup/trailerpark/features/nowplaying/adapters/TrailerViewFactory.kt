package com.shakeup.trailerpark.features.nowplaying.adapters

import android.view.View
import android.view.ViewGroup
import com.shakeup.trailerpark.R
import com.shakeup.trailerpark.commons.inflate

/**
 * Created by Jayson on 9/17/2017.
 *
 * Factory class that provides recycles objects. It provides TrailerView objects from
 * the bucket if they exist, and creates new ones if there aren't enough
 */

class TrailerViewFactory {
    private val recyclingBucket = ArrayList<View>(emptyList<View>())

    /**
     * Provides a Trailer cardview if one is available. Inflates a new one if required.
     */
    fun getView(parent: ViewGroup): View {
        when (recyclingBucket.isEmpty()) {
            false -> return recyclingBucket.removeAt(0)
            true -> return parent.inflate(R.layout.cardview_trailer, false)
        }
    }

    /**
     * Adds a Trailer cardview to the recycling bucket
     */
    fun recycleView(recycledView: View) {
        recyclingBucket.add(recycledView)
    }
}