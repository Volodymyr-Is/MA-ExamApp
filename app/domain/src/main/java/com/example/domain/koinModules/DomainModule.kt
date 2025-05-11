package com.example.domain.koinModules

import com.example.domain.useCases.AddFavoriteJokeUseCase
import com.example.domain.useCases.GetFavoriteJokesUseCase
import com.example.domain.useCases.GetJokeUseCase
import com.example.domain.useCases.RemoveFavoriteJokeUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetJokeUseCase(get()) }
    factory { AddFavoriteJokeUseCase(get()) }
    factory { RemoveFavoriteJokeUseCase(get()) }
    factory { GetFavoriteJokesUseCase(get()) }
}