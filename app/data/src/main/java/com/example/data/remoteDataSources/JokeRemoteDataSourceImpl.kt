package com.example.data.remoteDataSources

import android.content.Context
import com.example.domain.model.Joke

open class JokeRemoteDataSourceImpl(
    private val apiService: JokeApiService,
    private val context: Context
) : JokeRemoteDataSource {
    override suspend fun getJokeByCategory(category: String): Joke {
        val apiKey = provideRapidApiKey(context)
        val response = apiService.getJokeByCategory(category, apiKey)
        return Joke(
            category = category,
            text = response.data
        )
    }

    protected open fun provideRapidApiKey(context: Context): String {
        val resId = context.resources.getIdentifier("rapid_api_key", "string", context.packageName)
        return context.getString(resId)
    }
}