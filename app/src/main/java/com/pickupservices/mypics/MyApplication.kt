package com.pickupservices.mypics

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Initialization of libraries.
 */
@HiltAndroidApp
internal class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize Timber
        Timber.plant(Timber.DebugTree())
    }
}