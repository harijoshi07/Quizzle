package com.example.quizgame.data.repository

import com.example.quizgame.data.local.HistoryEntity
import com.example.quizgame.data.local.QuizDao
import com.example.quizgame.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow

class QuizRepository(
    private val apiService: ApiService,
    private val quizDao: QuizDao
) {
    suspend fun getQuiz(
        amount: Int,
        category: Int,
        difficulty: String?,
        type: String?
    ) = apiService.getQuiz(amount, category, difficulty, type)

    suspend fun insertHistory(
        historyEntity: HistoryEntity
    ) = quizDao.insertHistory(historyEntity)

    fun getHistory(): Flow<List<HistoryEntity>> = quizDao.getHistory()

    fun getLatestHistory():Flow<HistoryEntity> = quizDao.getLatestHistory()
    fun getHistoryById(id: Int): Flow<HistoryEntity> = quizDao.getHistoryById(id)
}