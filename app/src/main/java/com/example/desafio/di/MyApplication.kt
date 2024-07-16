package com.example.desafio.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Inicialize o Koin
        startKoin {
            // Fornecer o contexto Android
            androidContext(this@MyApplication)
            modules(myModule)
        }
    }
}
