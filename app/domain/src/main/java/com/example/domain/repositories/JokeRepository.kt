package com.example.domain.repositories

import com.example.domain.model.Joke
import kotlinx.coroutines.flow.Flow

interface JokeRepository {
    suspend fun getJokeByCategory(category: String): Joke
    suspend fun addFavoriteJoke(joke: Joke)
    suspend fun removeFavoriteJoke(jokeId: String)
    fun getFavoriteJokes(): Flow<List<Joke>>
}