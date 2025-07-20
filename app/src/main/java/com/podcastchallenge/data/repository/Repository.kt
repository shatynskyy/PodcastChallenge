package com.podcastchallenge.data.repository

import androidx.paging.PagingData
import com.podcastchallenge.data.models.PodcastDTO
import com.podcastchallenge.data.room.PodcastEntity
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getBestPodcasts() : Flow<PagingData<PodcastDTO>>
    fun getFavoritesFlow(): Flow<List<PodcastEntity>>
    fun isFavoritePodcastFlow(id: String) : Flow<Boolean>
    suspend fun toggleFavorite(podcast: PodcastEntity, isFavorite: Boolean)
}