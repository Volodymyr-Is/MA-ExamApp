package com.example.domain.useCases

import com.example.domain.model.Joke
import com.example.domain.repositories.JokeRepository

class AddFavoriteJokeUseCase(private val repository: JokeRepository) {
    suspend operator fun invoke(joke: Joke) {
        repository.addFavoriteJoke(joke)
    }
}