package com.hous.hous_aos

import android.app.Application
import com.hous.hous_aos.util.TimberDebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class HousApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(TimberDebugTree())
    }
}
