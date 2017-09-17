package com.shakeup.trailerpark.commons

import android.support.v7.app.AppCompatActivity
import rx.subscriptions.CompositeSubscription
/**
 * Created by Jayson on 9/14/2017.
 *
 * Base activity that handles subscribing and unsubscribing items during the activity lifecycle
 */
open class RxBaseActivity : AppCompatActivity() {

    companion object RxSubscriptions {
        var subscriptions = CompositeSubscription()
    }

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeSubscription()
    }

    override fun onPause() {
        super.onPause()
        if (!subscriptions.isUnsubscribed) {
            subscriptions.unsubscribe()
        }
        subscriptions.clear()
    }
}