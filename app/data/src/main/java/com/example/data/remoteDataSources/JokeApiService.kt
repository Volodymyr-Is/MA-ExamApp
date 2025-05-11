package com.example.data.remoteDataSources

import retrofit2.http.GET
import com.example.domain.model.JokeResponse
import retrofit2.http.Header
import retrofit2.http.Path

interface JokeApiService {
    @GET("{category}")
    suspend fun getJokeByCategory(
        @Path("category") category: String,
        @Header("X-RapidAPI-Key") apiKey: String
    ): JokeResponse
}