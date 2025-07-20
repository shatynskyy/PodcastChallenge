package com.podcastchallenge.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.podcastchallenge.data.PodcastPagingSource
import com.podcastchallenge.data.models.PodcastDTO
import com.podcastchallenge.data.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {

    override fun getBestPodcasts(): Flow<PagingData<PodcastDTO>> {
        val pagingConfig = PagingConfig(pageSize = 20)
        val pagingSource = PodcastPagingSource(apiService)
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { pagingSource }
        ).flow
    }

}