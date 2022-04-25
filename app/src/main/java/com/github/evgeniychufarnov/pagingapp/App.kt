package com.github.evgeniychufarnov.pagingapp

import android.app.Application
import com.github.evgeniychufarnov.pagingapp.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(mainModule)
        }
    }
}