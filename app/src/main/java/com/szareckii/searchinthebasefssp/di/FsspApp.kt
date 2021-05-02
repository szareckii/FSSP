package com.szareckii.searchinthebasefssp.di

import android.app.Application
import org.koin.core.context.startKoin

class FsspApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}