package com.example.presentation.navigation

sealed class Directions(val route: String) {
    data class Categories(val direction: String = "categories")  : Directions(direction)
    data class JokeDetail(val endpoint: String, val displayName: String) : Directions("joke/$endpoint/$displayName")
    data class Favorites(val direction: String = "favorites")  : Directions(direction)
}