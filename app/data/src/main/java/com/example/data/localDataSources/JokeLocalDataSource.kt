package com.example.data.localDataSources

import com.example.domain.model.Joke

interface JokeLocalDataSource {
    suspend fun saveJoke(joke: Joke)
    suspend fun getAllJokes(): List<Joke>
    suspend fun deleteJokeById(id: String)
}