package com.example.data.koinModules

import android.content.Context
import androidx.room.Room
import com.example.data.dao.JokeDao
import com.example.data.database.JokeDatabase
import com.example.data.localDataSources.JokeLocalDataSource
import com.example.data.localDataSources.JokeLocalDataSourceImpl
import com.example.data.remoteDataSources.JokeApiService
import com.example.data.remoteDataSources.JokeRemoteDataSource
import com.example.data.remoteDataSources.JokeRemoteDataSourceImpl
import com.example.data.repository.JokeRepositoryImpl
import com.example.domain.repositories.JokeRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dataModule = module {
    single { provideRapidApiKey(get()) }
    single { provideMoshiBuilder() }
    single { provideRetrofit(get(), get()) }
    single { provideJokeApiService(get()) }
    single { provideDatabaseBuilder(get()) }

    single<JokeDao> { get<JokeDatabase>().jokeDao() }

    single<JokeLocalDataSource> { JokeLocalDataSourceImpl(get()) }
    single<JokeRemoteDataSource> { JokeRemoteDataSourceImpl(get(), get()) }
    single<JokeRepository> { JokeRepositoryImpl(get(), get()) }
}

private fun provideRapidApiKey(context: Context): String {
    val resId = context.resources.getIdentifier("rapid_api_key", "string", context.packageName)
    return context.getString(resId)
}

private fun provideRetrofit(rapidApiKey: String, moshi: Moshi): Retrofit {
    val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("X-RapidAPI-Key", rapidApiKey)
                .header("X-RapidAPI-Host", "jokes-always.p.rapidapi.com")
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }
        .build()

    return Retrofit.Builder()
        .baseUrl("https://jokes-always.p.rapidapi.com/")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}

private fun provideJokeApiService(retrofit: Retrofit): JokeApiService {
    return retrofit.create(JokeApiService::class.java)
}

private fun provideMoshiBuilder(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

private fun provideDatabaseBuilder(context: Context): JokeDatabase {
    return Room.databaseBuilder(
        context,
        JokeDatabase::class.java,
        "joke_database"
    ).build()
}