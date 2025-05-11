package com.example.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Joke
import com.example.domain.useCases.AddFavoriteJokeUseCase
import com.example.domain.useCases.GetFavoriteJokesUseCase
import com.example.domain.useCases.GetJokeUseCase
import com.example.domain.useCases.RemoveFavoriteJokeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.UUID

class JokeViewModel(
    private val getJokeUseCase: GetJokeUseCase,
    private val addFavoriteJokeUseCase: AddFavoriteJokeUseCase,
    private val removeFavoriteJokeUseCase: RemoveFavoriteJokeUseCase,
    private val getFavoriteJokesUseCase: GetFavoriteJokesUseCase
) : ViewModel() {
    private val _joke = MutableStateFlow<Joke?>(null)
    val joke: StateFlow<Joke?> = _joke

    private val _favoriteJokes = MutableStateFlow<List<Joke>>(emptyList())
    val favoriteJokes: StateFlow<List<Joke>> = _favoriteJokes

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadFavoriteJokes()
    }

    fun loadJoke(category: String) {
        viewModelScope.launch {
            try {
                val joke = getJokeUseCase(category)
                val jokeWithId = if (joke.id.isEmpty()) {
                    joke.copy(id = UUID.randomUUID().toString())
                } else {
                    joke
                }
                _joke.value = jokeWithId
            } catch (e: Exception) {
                _error.value = "Failed to load joke: ${e.message}"
            }
        }
    }

    fun addToFavorites(joke: Joke) {
        viewModelScope.launch {
            try {
                val jokeWithId = if (joke.id.isEmpty()) {
                    joke.copy(id = UUID.randomUUID().toString())
                } else {
                    joke
                }
                addFavoriteJokeUseCase(jokeWithId)
                loadFavoriteJokes()
            } catch (e: Exception) {
                _error.value = "Failed to add to favorites: ${e.message}"
            }
        }
    }

    fun removeFavoriteJoke(jokeId: String) {
        viewModelScope.launch {
            try {
                removeFavoriteJokeUseCase(jokeId)
                loadFavoriteJokes()
            } catch (e: Exception) {
                _error.value = "Failed to remove from favorites: ${e.message}"
            }
        }
    }

    private fun loadFavoriteJokes() {
        viewModelScope.launch {
            getFavoriteJokesUseCase()
                .catch { e ->
                    _error.value = "Failed to load favorite jokes: ${e.message}"
                }
                .collect { jokes ->
                    _favoriteJokes.value = jokes
                }
        }
    }

    fun clearError() {
        _error.value = null
    }
}