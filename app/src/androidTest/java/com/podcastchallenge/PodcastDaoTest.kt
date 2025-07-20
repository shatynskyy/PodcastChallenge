package com.podcastchallenge

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.podcastchallenge.data.room.PodcastDao
import com.podcastchallenge.data.room.PodcastEntity
import com.podcastchallenge.data.room.RoomDB
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PodcastDaoTest {

    private lateinit var db: RoomDB
    private lateinit var dao: PodcastDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RoomDB::class.java
        ).allowMainThreadQueries().build()

        dao = db.podcastDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insert_addsPodcastToFavorites() = runTest {
        val podcast = PodcastEntity(id = "insert123")
        dao.insert(podcast)

        val favorites = dao.getFavoritesFlow().first()
        assertTrue(favorites.any { it.id == "insert123" })
    }

    @Test
    fun deleteById_removesPodcastFromFavorites() = runTest {
        val podcast = PodcastEntity(id = "delete123")
        dao.insert(podcast)
        dao.deleteById("delete123")

        val favorites = dao.getFavoritesFlow().first()
        assertTrue(favorites.none { it.id == "delete123" })
    }

    @Test
    fun isFavoritePodcastFlow_returnsTrueIfExists() = runTest {
        val podcast = PodcastEntity(id = "exist123")
        dao.insert(podcast)

        val isFavorite = dao.isFavoritePodcastFlow("exist123").first()
        assertTrue(isFavorite)
    }

    @Test
    fun isFavoritePodcastFlow_returnsFalseIfNotExists() = runTest {
        val isFavorite = dao.isFavoritePodcastFlow("missing").first()
        assertFalse(isFavorite)
    }
}