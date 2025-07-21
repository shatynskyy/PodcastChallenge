package com.podcastchallenge.application.screens.podcastdetail

import com.podcastchallenge.application.models.PodcastPresentation

/**
 * Defines the state and user events for the Podcast Detail screen.
 *
 * Part of the MVI architecture:
 * - [PodcastDetailState] represents the current UI state.
 * - [PodcastDetailEvents] defines all possible user interactions.
 */
data class PodcastDetailState(
    val isFavorite: Boolean = false
)

sealed class PodcastDetailEvents {
    data class ToggleFavorite(val podcast: PodcastPresentation) : PodcastDetailEvents()
    data class CheckIfFavorite(val id: String) : PodcastDetailEvents()
}