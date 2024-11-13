package com.example.quizgame.data.local

import androidx.room.RoomDatabase


abstract class QuizDatabase: RoomDatabase() {

    abstract fun quizDao(): QuizDao
}