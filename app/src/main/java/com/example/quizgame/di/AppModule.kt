package com.example.quizgame.di

import com.example.quizgame.data.SettingPreferences
import com.example.quizgame.data.dataStore
import com.example.quizgame.data.remote.retrofit.ApiConfig
import com.example.quizgame.ui.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        val settingPreferences = SettingPreferences(androidContext().dataStore)
        settingPreferences
    }
}

val networkModule = module {
    single {
        val apiService = ApiConfig.getApiService();
    }
}


val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}