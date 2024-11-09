package com.example.quizgame

import android.app.Application
import com.example.quizgame.di.appModule
import com.example.quizgame.di.networkModule
import com.example.quizgame.di.repositoryModule
import com.example.quizgame.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule, networkModule, repositoryModule, viewModelModule)
        }
    }
}