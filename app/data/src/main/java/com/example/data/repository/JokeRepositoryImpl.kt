package com.example.data.repository

import com.example.data.localDataSources.JokeLocalDataSource
import com.example.data.remoteDataSources.JokeRemoteDataSource
import com.example.domain.model.Joke
import com.example.domain.repositories.JokeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class JokeRepositoryImpl(
    private val localDataSource: JokeLocalDataSource,
    private val remoteDataSource: JokeRemoteDataSource
) : JokeRepository {
    override suspend fun getJokeByCategory(category: String): Joke {
        return remoteDataSource.getJokeByCategory(category)
    }

    override suspend fun addFavoriteJoke(joke: Joke) {
        localDataSource.saveJoke(joke)
    }

    override suspend fun removeFavoriteJoke(jokeId: String) {
        localDataSource.deleteJokeById(jokeId)
    }

    override fun getFavoriteJokes(): Flow<List<Joke>> {
        return flow {
            val jokes = localDataSource.getAllJokes()
            emit(jokes)
        }
    }
}