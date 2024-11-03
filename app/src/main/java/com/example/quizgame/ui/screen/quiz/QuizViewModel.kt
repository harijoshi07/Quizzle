package com.example.quizgame.ui.screen.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizgame.data.SettingsQuiz
import com.example.quizgame.data.remote.response.ResponseState
import com.example.quizgame.data.remote.response.ResultsItem
import com.example.quizgame.data.repository.QuizRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuizViewModel(private val quizRepository: QuizRepository) : ViewModel() {

    val _quiz = MutableStateFlow(ResponseState<List<ResultsItem>>())
    val quiz: StateFlow<ResponseState<List<ResultsItem>>> = _quiz

    fun quiz(settings: SettingsQuiz) {

        viewModelScope.launch {

            try {
                val response = quizRepository.getQuiz(
                    amount = settings.amount,
                    category = settings.category,
                    difficulty = if (settings.difficulty == "Mixed") null else settings.difficulty,
                    type = if (settings.type == "Mixed") null else settings.type
                )

                if (response.responseCode == 0) {
                    _quiz.value = _quiz.value.copy(
                        loading = false,
                        data = response.results,
                        error = null
                    )
                    Log.i("QuizViewModel", "getQuiz: ${response.results}")

                } else {
                    _quiz.value = _quiz.value.copy(
                        loading = false,
                        data = null,
                        error = "Error null data"
                    )
                }

            } catch (e: Exception) {
                _quiz.value = _quiz.value.copy(
                    loading = false,
                    data = null,
                    error = e.message
                )
            }
        }
    }
}