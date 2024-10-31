package com.example.quizgame.di

import com.example.quizgame.ui.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val appModule = module {

    viewModel { MainViewModel() }
}