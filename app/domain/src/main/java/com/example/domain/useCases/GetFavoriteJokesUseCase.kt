package com.example.domain.useCases

import com.example.domain.model.Joke
import com.example.domain.repositories.JokeRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteJokesUseCase(private val repository: JokeRepository) {
    operator fun invoke(): Flow<List<Joke>> {
        return repository.getFavoriteJokes()
    }
}