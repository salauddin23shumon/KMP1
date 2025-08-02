package org.s1s.project

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.s1s.project.di.init.initKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MainApplication)
        }
    }
}