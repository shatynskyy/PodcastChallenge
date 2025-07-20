package com.podcastchallenge.application.screens.podcastdetail

import com.podcastchallenge.application.models.PodcastPresentation

data class PodcastDetailState(
    val isFavorite: Boolean = false
)

sealed class PodcastDetailEvents {
    data class ToggleFavorite(val podcast: PodcastPresentation) : PodcastDetailEvents()
    data class CheckIfFavorite(val id: String) : PodcastDetailEvents()
}