package com.podcastchallenge.data.repository

import androidx.paging.PagingData
import com.podcastchallenge.data.models.PodcastDTO
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getBestPodcasts() : Flow<PagingData<PodcastDTO>>

}