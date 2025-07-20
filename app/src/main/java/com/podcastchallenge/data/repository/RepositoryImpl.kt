package com.podcastchallenge.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.podcastchallenge.data.PodcastPagingSource
import com.podcastchallenge.data.models.PodcastDTO
import com.podcastchallenge.data.retrofit.ApiService
import com.podcastchallenge.data.room.PodcastDao
import com.podcastchallenge.data.room.PodcastEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val podcastDao: PodcastDao
) : Repository {

    override fun getBestPodcasts() : Flow<PagingData<PodcastDTO>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = {
                PodcastPagingSource(apiService) }
        ).flow
    }

    override fun getFavoritesFlow(): Flow<List<PodcastEntity>> {
        return podcastDao.getFavoritesFlow()
    }

    override suspend fun toggleFavorite(
        podcast: PodcastEntity,
        isFavorite: Boolean
    ) {
        if (isFavorite) {
            podcastDao.deleteById(podcast.id)
        } else {
            podcastDao.insert(podcast)
        }
    }

    override fun isFavoritePodcastFlow(id: String): Flow<Boolean> {
        return podcastDao.isFavoritePodcastFlow(id = id)
    }

}