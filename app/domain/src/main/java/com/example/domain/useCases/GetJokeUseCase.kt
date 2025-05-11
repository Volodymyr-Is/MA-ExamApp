package com.example.domain.useCases

import com.example.domain.model.Joke
import com.example.domain.repositories.JokeRepository

class GetJokeUseCase(private val repository: JokeRepository) {
    suspend operator fun invoke(category: String): Joke {
        return repository.getJokeByCategory(category)
    }
}