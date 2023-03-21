package com.youtop.app

import android.app.Application
import com.youtop.di.appKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class YouTopApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@YouTopApp)
            modules(appKoinModule)
        }
    }
}