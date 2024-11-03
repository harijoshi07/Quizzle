package com.example.quizgame.data.remote.response

data class ResponseState<T>(
    val loading: Boolean = true,
    val data: T? = null,
    val error: String? = null
)