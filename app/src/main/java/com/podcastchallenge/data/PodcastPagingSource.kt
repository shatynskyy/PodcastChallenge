package com.podcastchallenge.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.podcastchallenge.data.models.PodcastDTO
import com.podcastchallenge.data.retrofit.ApiService

class PodcastPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, PodcastDTO>() {

    override fun getRefreshKey(state: PagingState<Int, PodcastDTO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PodcastDTO> {
        return try {

            val currentPage = params.key ?: 0

            val response = apiService.getBestPodcasts()

            val nextKey = if (response.hasNext == true) currentPage + 1 else null
            val prevKey = if (currentPage > 0) currentPage - 1 else null

            return LoadResult.Page(
                data = response.podcasts,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}