package com.podcastchallenge.data.repository

import com.podcastchallenge.data.retrofit.ApiService
import com.podcastchallenge.data.room.PodcastDao
import com.podcastchallenge.data.room.PodcastEntity
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for [RepositoryImpl], which handles podcast data operations
 * such as fetching best podcasts, managing favorites, and checking favorite status.
 *
 * These tests verify that [RepositoryImpl] correctly delegates to [PodcastDao] and
 * behaves as expected when toggling favorite status.
 */
class RepositoryImplTest {

    private val apiService: ApiService = mockk()
    private val dao: PodcastDao = mockk(relaxed = true)
    private lateinit var repository: RepositoryImpl

    @Before
    fun setup() {
        repository = RepositoryImpl(apiService, dao)
    }

    @Test
    fun `toggleFavorite inserts podcast when not favorite`() = runTest {
        val podcast = PodcastEntity("id_1")

        repository.toggleFavorite(podcast, isFavorite = false)

        coVerify { dao.insert(PodcastEntity("id_1")) }
        coVerify(exactly = 0) { dao.deleteById(any()) }
    }

    @Test
    fun `toggleFavorite deletes podcast when already favorite`() = runTest {
        val podcast = PodcastEntity("id_2")

        repository.toggleFavorite(podcast, isFavorite = true)

        coVerify { dao.deleteById("id_2") }
        coVerify(exactly = 0) { dao.insert(any()) }
    }

    @Test
    fun `getFavoritesFlow proxies to dao`() = runTest {
        val expected = listOf(PodcastEntity("id_1"))
        every { dao.getFavoritesFlow() } returns flowOf(listOf(PodcastEntity("id_1")))

        val result = repository.getFavoritesFlow().first()

        assertEquals(expected, result)
    }

    @Test
    fun `isFavoritePodcastFlow proxies to dao`() = runTest {
        every { dao.isFavoritePodcastFlow("test_id") } returns flowOf(true)

        val result = repository.isFavoritePodcastFlow("test_id").first()

        assertTrue(result)
    }

}