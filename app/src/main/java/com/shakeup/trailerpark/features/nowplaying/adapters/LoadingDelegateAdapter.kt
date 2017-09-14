package com.shakeup.trailerpark.features.nowplaying.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.shakeup.trailerpark.R
import com.shakeup.trailerpark.commons.adapters.ViewType
import com.shakeup.trailerpark.commons.adapters.ViewTypeDelegateAdapter
import com.shakeup.trailerpark.commons.inflate

/**
 * Created by Jayson on 9/14/2017.
 *
 * Delegate Adapter to show a Loading listitem
 */
class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.listitem_loading))
}