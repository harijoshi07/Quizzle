package com.example.quizgame.data.repository

import com.example.quizgame.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow

class QuizRepository(
    private val apiService: ApiService,
) {
    suspend fun getQuiz(
        amount: Int,
        category: Int,
        difficulty: String?,
        type: String?
    ) = apiService.getQuiz(amount, category, difficulty, type)
}