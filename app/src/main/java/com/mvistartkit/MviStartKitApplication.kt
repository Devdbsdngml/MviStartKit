package com.mvistartkit

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MviStartKitApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
