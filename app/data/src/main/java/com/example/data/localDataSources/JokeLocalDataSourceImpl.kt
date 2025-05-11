package com.example.data.localDataSources

import com.example.data.dao.JokeDao
import com.example.data.entities.JokeEntity
import com.example.domain.model.Joke

open class JokeLocalDataSourceImpl(private val jokeDao: JokeDao) : JokeLocalDataSource {
    override suspend fun saveJoke(joke: Joke) {
        val entity = JokeEntity(
            id = joke.id,
            category = joke.category,
            text = joke.text
        )
        jokeDao.insertJoke(entity)
    }

    override suspend fun getAllJokes(): List<Joke> {
        return jokeDao.getAllJokes().map { entity ->
            Joke(
                id = entity.id,
                category = entity.category,
                text = entity.text
            )
        }
    }

    override suspend fun deleteJokeById(id: String) {
        jokeDao.deleteJokeById(id)
    }
}