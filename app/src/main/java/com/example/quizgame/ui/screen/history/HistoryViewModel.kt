package com.example.quizgame.ui.screen.history

import androidx.lifecycle.ViewModel
import com.example.quizgame.data.local.HistoryEntity
import com.example.quizgame.data.repository.QuizRepository
import kotlinx.coroutines.flow.Flow

class HistoryViewModel(
    private val repository: QuizRepository
): ViewModel(){
    fun getHistory(): Flow<List<HistoryEntity>> {
        return repository.getHistory()
    }
}