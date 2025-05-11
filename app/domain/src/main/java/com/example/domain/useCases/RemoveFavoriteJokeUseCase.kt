package com.example.domain.useCases

import com.example.domain.repositories.JokeRepository

class RemoveFavoriteJokeUseCase(private val repository: JokeRepository) {
    suspend operator fun invoke(jokeId: String) {
        repository.removeFavoriteJoke(jokeId)
    }
}