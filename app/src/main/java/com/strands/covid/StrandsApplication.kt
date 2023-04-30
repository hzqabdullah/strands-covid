package com.strands.covid

import android.app.Application
import com.strands.covid.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StrandsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@StrandsApplication)
            modules(appModule)
        }
    }
}
