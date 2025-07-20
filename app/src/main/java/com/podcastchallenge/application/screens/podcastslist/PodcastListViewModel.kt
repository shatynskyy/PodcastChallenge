package com.podcastchallenge.application.screens.podcastslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.podcastchallenge.application.models.toPodcastPresentation
import com.podcastchallenge.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PodcastListViewModel @Inject constructor(
    repository: Repository
) : ViewModel() {


    val bestPodcastFlow = repository
        .getBestPodcasts()
        .map { pagingData -> pagingData.map { it.toPodcastPresentation() } }
        .cachedIn(viewModelScope)


}