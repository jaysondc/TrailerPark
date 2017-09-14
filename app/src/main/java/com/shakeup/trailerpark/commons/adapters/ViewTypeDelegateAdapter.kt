package com.shakeup.trailerpark.commons.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by Jayson on 9/14/2017.
 *
 * Interface a class can implement to be used as an adapter in a RecyclerView
 */
interface ViewTypeDelegateAdapter {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}