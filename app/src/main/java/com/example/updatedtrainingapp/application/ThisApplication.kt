package com.example.updatedtrainingapp.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ThisApplication : Application() {

    companion object {
        lateinit var instance: ThisApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}