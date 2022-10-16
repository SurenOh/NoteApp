package com.example.noteapp

import android.app.Application
import com.example.noteapp.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(applicationModule))
        }
    }
}