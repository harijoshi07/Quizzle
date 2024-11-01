package com.example.quizgame.data.repository

import com.example.quizgame.data.remote.retrofit.ApiService

class AuthRepository(private val apiService: ApiService) {

    suspend fun register() = apiService.register()
    suspend fun login() = apiService.login()
}