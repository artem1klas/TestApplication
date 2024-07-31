package com.example.testapplication.utils

import android.app.Application
import com.example.testapplication.di.dataModule
import com.example.testapplication.di.interactoreModule
import com.example.testapplication.di.repositoryModule
import com.example.testapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(viewModelModule, interactoreModule, repositoryModule, dataModule)
        }
    }
}