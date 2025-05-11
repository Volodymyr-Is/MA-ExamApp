package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.entities.JokeEntity

@Dao
interface JokeDao {
    @Insert
    suspend fun insertJoke(joke: JokeEntity)

    @Query("SELECT * FROM jokes")
    suspend fun getAllJokes(): List<JokeEntity>

    @Query("DELETE FROM jokes WHERE id = :id")
    suspend fun deleteJokeById(id: String)
}