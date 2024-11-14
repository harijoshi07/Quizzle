package com.example.quizgame.di

import androidx.room.Room
import com.example.quizgame.data.SettingPreferences
import com.example.quizgame.data.dataStore
import com.example.quizgame.data.local.QuizDatabase
import com.example.quizgame.data.remote.retrofit.ApiConfig
import com.example.quizgame.data.repository.QuizRepository
import com.example.quizgame.ui.MainViewModel
import com.example.quizgame.ui.screen.history.HistoryViewModel
import com.example.quizgame.ui.screen.quiz.QuizViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        val settingPreferences = SettingPreferences(androidContext().dataStore)
        settingPreferences
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            QuizDatabase::class.java, "quiz.db"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
    single { get<QuizDatabase>().quizDao() }
}

val networkModule = module {
    single {
        val apiService = ApiConfig.getApiService()
        apiService
    }
}

val repositoryModule = module {
    single { QuizRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { QuizViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
}