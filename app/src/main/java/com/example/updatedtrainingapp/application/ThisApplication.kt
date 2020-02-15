package com.example.updatedtrainingapp.application

import android.app.Application
import com.example.updatedtrainingapp.di.AppComponent
import com.example.updatedtrainingapp.di.AppModule
import com.example.updatedtrainingapp.di.DaggerAppComponent

class ThisApplication : Application() {

    lateinit var appComponent: AppComponent
    companion object {
        lateinit var instance: ThisApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = initDagger(this)
    }

    private fun initDagger(application: ThisApplication): AppComponent =
        DaggerAppComponent.builder().appModule(AppModule(application)).build()
}