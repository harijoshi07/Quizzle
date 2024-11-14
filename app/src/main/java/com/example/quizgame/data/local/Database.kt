package com.example.quizgame.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class], version = 1, exportSchema = false)
abstract class QuizDatabase: RoomDatabase() {

    abstract fun quizDao(): QuizDao
}