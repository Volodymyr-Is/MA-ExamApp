package com.example.data.repository

import com.example.data.localDataSources.JokeLocalDataSource
import com.example.data.remoteDataSources.JokeRemoteDataSource
import com.example.domain.model.Joke
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class JokeRepositoryImplTest {
    private lateinit var repository: JokeRepositoryImpl

    private val localDataSource by lazy { MockLocalDataSource() }
    private val remoteDataSource by lazy { MockRemoteDataSource() }

    @Before
    fun setUp() {
        repository = JokeRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Test
    fun `check jokes retrieved by category from remote`() = runBlocking {
        val category = "family"

        val result1 = repository.getJokeByCategory(category)
        val result2 = repository.getJokeByCategory(category)

        assertEquals("1", result1.id)
        assertEquals(category, result1.category)
        assertEquals("Why did the scarecrow become a motivational speaker?", result1.text)
        assertEquals("2", result2.id)
        assertEquals(category, result2.category)
        assertEquals("Why don't skeletons fight each other? They don't have the guts.", result2.text)
    }

    @Test
    fun `check favorite jokes retrieved from local`() = runBlocking {
        val joke1 = Joke("1", "family", "Why did the scarecrow become a motivational speaker?")
        val joke2 = Joke("2", "animal", "Why did the cow cross the road?")
        localDataSource.saveJoke(joke1)
        localDataSource.saveJoke(joke2)

        val result = repository.getFavoriteJokes().first()

        assertEquals(2, result.size)
        assertEquals("family", result[0].category)
        assertEquals("animal", result[1].category)
    }

    @Test
    fun `check favorite jokes added to local`() = runBlocking {
        val joke1 = Joke("1", "family", "Why did the scarecrow become a motivational speaker?")
        val joke2 = Joke("2", "family", "Why don't skeletons fight each other? They don't have the guts.")

        repository.addFavoriteJoke(joke1)
        repository.addFavoriteJoke(joke2)

        val result = repository.getFavoriteJokes().first()
        assertEquals(2, result.size)
        assertEquals("family", result[0].category)
        assertEquals("family", result[1].category)
        assertEquals("Why did the scarecrow become a motivational speaker?", result[0].text)
        assertEquals("Why don't skeletons fight each other? They don't have the guts.", result[1].text)
    }

    @Test
    fun `check favorite jokes removed from local`() = runBlocking {
        val joke1 = Joke("1", "family", "Why did the scarecrow become a motivational speaker?")
        val joke2 = Joke("2", "family", "Why don't skeletons fight each other? They don't have the guts.")
        localDataSource.saveJoke(joke1)
        localDataSource.saveJoke(joke2)

        repository.removeFavoriteJoke("1")
        repository.removeFavoriteJoke("2")

        val result = repository.getFavoriteJokes().first()
        assertEquals(0, result.size)
    }

    @Test
    fun `check favorite jokes added and retrieved`() = runBlocking {
        val joke1 = Joke("1", "family", "Why did the scarecrow become a motivational speaker?")
        val joke2 = Joke("2", "animal", "Why did the cow cross the road?")

        repository.addFavoriteJoke(joke1)
        repository.addFavoriteJoke(joke2)

        val result = repository.getFavoriteJokes().first()
        assertEquals(2, result.size)
        assertEquals("family", result[0].category)
        assertEquals("animal", result[1].category)
        assertEquals("Why did the scarecrow become a motivational speaker?", result[0].text)
        assertEquals("Why did the cow cross the road?", result[1].text)
    }

    private class MockLocalDataSource : JokeLocalDataSource {
        private val jokes = mutableListOf<Joke>()

        override suspend fun saveJoke(joke: Joke) {
            jokes.add(joke)
        }

        override suspend fun getAllJokes(): List<Joke> {
            return jokes
        }

        override suspend fun deleteJokeById(id: String) {
            jokes.removeIf { it.id == id }
        }
    }

    private class MockRemoteDataSource : JokeRemoteDataSource {
        private var callCount = 0

        override suspend fun getJokeByCategory(category: String): Joke {
            return when (++callCount) {
                1 -> Joke("1", category, "Why did the scarecrow become a motivational speaker?")
                else -> Joke("2", category, "Why don't skeletons fight each other? They don't have the guts.")
            }
        }
    }
}