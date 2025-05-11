package com.example.data.remoteDataSources

import com.example.domain.model.Joke

interface JokeRemoteDataSource {
    suspend fun getJokeByCategory(category: String): Joke
}