package org.s1s.project

import android.app.Application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.s1s.project.di.init.initKoin


class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // debug log

        Napier.base(DebugAntilog())


        // koin
        initKoin {
            androidContext(this@MainApplication)
        }
    }
}