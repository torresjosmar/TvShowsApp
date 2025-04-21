package com.example.tvshowsapp

import android.app.Application
import com.example.tvshowsapp.core.network.networkModule
import com.example.tvshowsapp.feature.home.module.tvShowModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level

class BaseTvShowApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger(Level.INFO)
            androidContext(this@BaseTvShowApplication)
            koin.loadModules(
                listOf(
                    networkModule,
                    tvShowModule
                )
            )
        }
    }
}