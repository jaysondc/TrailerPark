package com.shakeup.trailerpark.commons

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.shakeup.trailerpark.R
import com.squareup.picasso.Picasso

/**
 * Created by Jayson on 9/14/2017.
 *
 * Kotlin extensions
 */

/**
 * Extension to inflate a layout straight from a ViewGroup
 */
fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

/**
 * Extension to load an image straight from an ImageView
 */
fun ImageView.loadImg(imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl)) {
        Picasso.with(context).load(R.mipmap.ic_launcher).into(this)
    } else {
        Picasso.with(context).load(imageUrl).into(this)
    }
}