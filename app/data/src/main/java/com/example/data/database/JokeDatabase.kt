package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.dao.JokeDao
import com.example.data.entities.JokeEntity

@Database(entities = [JokeEntity::class], version = 2)
abstract class JokeDatabase : RoomDatabase() {
    abstract fun jokeDao(): JokeDao
}