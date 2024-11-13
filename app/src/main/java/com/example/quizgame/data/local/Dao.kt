package com.example.quizgame.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: HistoryEntity)

    //select all quizzes user played from database
    @Query("SELECT * FROM history ORDER BY id DESC")
    fun getHistory(): Flow<List<HistoryEntity>>

    //select latest quiz that user played
    @Query("SELECT * FROM history ORDER BY id DESC LIMIT 1")
    fun getLatestHistory(): Flow<HistoryEntity>
}


