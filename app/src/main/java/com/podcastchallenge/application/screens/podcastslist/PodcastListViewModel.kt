package com.podcastchallenge.application.screens.podcastslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.podcastchallenge.application.models.PodcastPresentation
import com.podcastchallenge.application.models.toPodcastPresentation
import com.podcastchallenge.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PodcastListViewModel @Inject constructor(
    repository: Repository
) : ViewModel() {

    private val favoriteIdsFlow: Flow<Set<String>> =
        repository.getFavoritesFlow()
            .map { list -> list.map { it.id }.toSet() }
            .distinctUntilChanged()

    private val basePagingFlow = repository
        .getBestPodcasts()
        .cachedIn(viewModelScope)


    val podcastsPagingFlow: Flow<PagingData<PodcastPresentation>> =
        combine(
            basePagingFlow,
            favoriteIdsFlow
        ) { pagingData, favoritesSet ->
            pagingData.map { dto ->
                dto.toPodcastPresentation(
                    isFavorite = favoritesSet.contains(dto.id)
                )
            }
        }
}