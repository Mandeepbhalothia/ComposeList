package com.learn.composelist

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ComposeListApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}