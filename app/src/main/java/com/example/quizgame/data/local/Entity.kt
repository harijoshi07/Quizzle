package com.example.quizgame.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "history")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val quiz: String,
    val category: String,
    val correctAnswer: Int,
    val size: Int,
    val question: String,
    val userAnswerData: String,
    val correctAnswerData: String,
)