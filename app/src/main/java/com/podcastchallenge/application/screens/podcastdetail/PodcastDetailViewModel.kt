package com.podcastchallenge.application.screens.podcastdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.podcastchallenge.application.launchSafe
import com.podcastchallenge.application.models.PodcastPresentation
import com.podcastchallenge.application.models.toPodcastEntity
import com.podcastchallenge.application.screens.podcastdetail.PodcastDetailEvents.*
import com.podcastchallenge.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class PodcastDetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow(PodcastDetailState())
    val state: StateFlow<PodcastDetailState> = _state.asStateFlow()

    private suspend fun toggleFavorite(podcast: PodcastPresentation) {
        repository.toggleFavorite(
            podcast = podcast.toPodcastEntity(),
            isFavorite = _state.value.isFavorite
        )
    }

    private suspend fun checkIfFavorite(id: String) {
        repository.isFavoritePodcastFlow(id).collect { isFav ->
            _state.update { it.copy(isFavorite = isFav) }
        }
    }


    fun onDispatch(event: PodcastDetailEvents) {
        viewModelScope.launchSafe {
            when (event) {
                is ToggleFavorite -> toggleFavorite(event.podcast)
                is CheckIfFavorite -> checkIfFavorite(event.id)
            }
        }
    }

}